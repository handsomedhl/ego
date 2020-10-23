package com.ego.order.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ego.commons.pojo.EgoResult;
import com.ego.commons.pojo.TbItemChild;
import com.ego.commons.utils.CookieUtils;
import com.ego.commons.utils.HttpClientUtil;
import com.ego.commons.utils.IDUtils;
import com.ego.commons.utils.JsonUtils;
import com.ego.dubbo.service.TbItemDubboService;
import com.ego.dubbo.service.TbOrderDubboService;
import com.ego.order.pojo.MyOrderParam;
import com.ego.order.service.TbOrderService;
import com.ego.pojo.TbItem;
import com.ego.pojo.TbOrder;
import com.ego.pojo.TbOrderItem;
import com.ego.pojo.TbOrderShipping;
import com.ego.redis.dao.JedisDao;
@Service
public class TbOrderServiceImpl implements TbOrderService{
	@Resource
	private JedisDao jedisDaoImpl;
	@Reference
	private TbItemDubboService tbItemDubboService;
	@Reference
	private TbOrderDubboService tbOrderDubboService;
	@Value("${passport.url}")
	private String passportUrl;
	@Value("${cart.key}")
	private String cartKey;
	@Override
	public List<TbItemChild> showOrder(List<Long> ids,HttpServletRequest request) {
		String token = CookieUtils.getCookieValue(request, "TT_TOKEN");
		String jsonUser = HttpClientUtil.doPost(passportUrl+token);
		EgoResult er = JsonUtils.jsonToPojo(jsonUser, EgoResult.class);
		String key = cartKey + ((LinkedHashMap)er.getData()).get("username");
		String json = jedisDaoImpl.get(key);
		List<TbItemChild> newList = new ArrayList<TbItemChild>();
		if(json != null && !json.equals("")) {
			List<TbItemChild> list = JsonUtils.jsonToList(json, TbItemChild.class);
			for (TbItemChild item : list) {
				for (long id : ids) {
					if((long)item.getId() == id) {
						newList.add(item);
					}
				}
			}
		}
		for (TbItemChild tbItemChild : newList) {
			int num = tbItemDubboService.selById(tbItemChild.getId()).getNum();
			if(tbItemChild.getNum() > num) {
				tbItemChild.setEnough(false);
			}else {
				tbItemChild.setEnough(true);
			}
		}
		return newList;
	}
	@Override
	public EgoResult create(MyOrderParam param, HttpServletRequest request) {
		//订单表数据
//		System.out.println(param);
		TbOrder order = new TbOrder();
		long id = IDUtils.genItemId();
		order.setOrderId(id + "");
		order.setPayment(param.getPayment());
		order.setPaymentType(param.getPaymentType());
		Date date = new Date();
		order.setCreateTime(date);
		order.setUpdateTime(date);
		String token = CookieUtils.getCookieValue(request, "TT_TOKEN");
		String jsonUser = HttpClientUtil.doPost(passportUrl + token);
		EgoResult er = JsonUtils.jsonToPojo(jsonUser, EgoResult.class);
		//买家信息
		Map user = (LinkedHashMap)er.getData();
		order.setUserId(Long.parseLong(user.get("id").toString()));
		order.setBuyerNick(user.get("username").toString());
		order.setBuyerRate(0);
		//订单商品表消息
		List<TbOrderItem> orderItems = param.getOrderItems();
		for (TbOrderItem tbOrderItem : orderItems) {
			tbOrderItem.setId(IDUtils.genItemId() + "");
			tbOrderItem.setOrderId(id + "");
		}
		TbOrderShipping orderShipping = param.getOrderShipping();
		orderShipping.setOrderId(id + "");
		orderShipping.setCreated(date);
		orderShipping.setUpdated(date);
		EgoResult egoResult = new EgoResult();
		try {
			int index = tbOrderDubboService.insOrder(order, orderItems, orderShipping);
			if(index > 0) {
				egoResult.setStatus(200);
				String key = cartKey + user.get("username").toString();
				String json = jedisDaoImpl.get(key);
				List<TbItemChild> items = JsonUtils.jsonToList(json, TbItemChild.class);
				//对购物车已经结算了的商品进行删除
				boolean flag = false;
				for (int i = items.size() - 1; i >= 0; i--) {
					flag = false;
					for (TbOrderItem tbOrderItem : orderItems) {
						if((long)items.get(i).getId() == Long.parseLong(tbOrderItem.getItemId())){
							flag = true;
						}
					}
					if(flag) {
						items.remove(i);
					}
				}
				//将删除后的购物车信息重新放入redis中
				jedisDaoImpl.set(key, JsonUtils.objectToJson(items));
			}
		} catch (Exception e) {
			egoResult.setMsg("创建订单失败");;
			e.printStackTrace();
		}
		return egoResult;
	}

}
