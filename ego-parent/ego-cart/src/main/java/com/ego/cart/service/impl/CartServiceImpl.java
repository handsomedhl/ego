package com.ego.cart.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ego.cart.service.CartService;
import com.ego.commons.pojo.EgoResult;
import com.ego.commons.pojo.TbItemChild;
import com.ego.commons.utils.CookieUtils;
import com.ego.commons.utils.JsonUtils;
import com.ego.dubbo.service.TbItemDubboService;
import com.ego.pojo.TbItem;
import com.ego.pojo.TbItemDesc;
import com.ego.pojo.TbUser;
import com.ego.redis.dao.JedisDao;

@Service
public class CartServiceImpl implements CartService {
	@Resource
	private JedisDao jedisDaoImpl;
	@Reference
	private TbItemDubboService tbItemDubboServiceImpl;
	@Value("${passport.url}")
	private String passportUrl;
	@Value("${cart.key}")
	private String cartKey;

	@Override
	public void addCart(long id, int num, HttpServletRequest request) {
		List<TbItemChild> list = new ArrayList<>();
		//生成redis中购物车信息的key
		String token = CookieUtils.getCookieValue(request, "TT_TOKEN");
		String json = jedisDaoImpl.get(token);
		TbUser tbUser = JsonUtils.jsonToPojo(json, TbUser.class);
		String key = cartKey + tbUser.getUsername();

		String jsonUser = jedisDaoImpl.get(key);
		// 如果redis中存储的购物车信息是否为空
		if (jsonUser != null && !jsonUser.equals("")) {
			list = JsonUtils.jsonToList(jsonUser, TbItemChild.class);
			for (TbItemChild tbItemChild : list) {
				// 最好强转，防止原始数据类型与引用类型比较错误
				if ((long) tbItemChild.getId() == id) {
					tbItemChild.setNum(tbItemChild.getNum() + num);
					jedisDaoImpl.set(key, JsonUtils.objectToJson(list));
					return;
				}
			}
		}
		// 如果redis中的key不存在或为空
		TbItemChild child = new TbItemChild();
		TbItem item = tbItemDubboServiceImpl.selById(id);
		child.setId(item.getId());
		child.setTitle(item.getTitle());
		child.setPrice(item.getPrice());
		child.setNum(num);
		child.setImages(item.getImage() == null ? new String[1] : item.getImage().split(","));
		list.add(child);
		jedisDaoImpl.set(key, JsonUtils.objectToJson(list));

	}

	@Override
	public List<TbItemChild> show(HttpServletRequest request) {
		// 获取存储购物车信息的key
		String token = CookieUtils.getCookieValue(request, "TT_TOKEN");
		String jsonUser = jedisDaoImpl.get(token);
		String key = cartKey + JsonUtils.jsonToPojo(jsonUser, TbUser.class).getUsername();
		// 查询购物车信息
		String json = jedisDaoImpl.get(key);

		return JsonUtils.jsonToList(json, TbItemChild.class);
	}

	@Override
	public EgoResult deleteById(long id, HttpServletRequest request) {
		// 获取存储购物车信息的key
		String token = CookieUtils.getCookieValue(request, "TT_TOKEN");
		String jsonUser = jedisDaoImpl.get(token);
		String key = cartKey + JsonUtils.jsonToPojo(jsonUser, TbUser.class).getUsername();
		// 查询购物车信息
		String json = jedisDaoImpl.get(key);
		List<TbItemChild> list = null;
		if(json != null && !json.equals("")) {
			list = JsonUtils.jsonToList(json, TbItemChild.class);
			TbItemChild temp = null;
			for (TbItemChild tbItemChild : list) {
				if(tbItemChild.getId() == id) {
					temp = tbItemChild;
				}
			}
			list.remove(temp);
		}
		EgoResult er = new EgoResult();
		String ok = jedisDaoImpl.set(key, JsonUtils.objectToJson(list));
		if(ok.equals("OK")) {
			er.setStatus(200);
		}
		return er;
	}

	@Override
	public EgoResult update(long id, int num, HttpServletRequest request) {
		String token = CookieUtils.getCookieValue(request, "TT_TOKEN");
		String jsonUser = jedisDaoImpl.get(token);
		String key = cartKey + JsonUtils.jsonToPojo(jsonUser, TbUser.class).getUsername();
		//// 查询购物车信息
		String json = jedisDaoImpl.get(key);
		List<TbItemChild> list = null;
		if(json != null && !json.equals("")) {
			list = JsonUtils.jsonToList(json, TbItemChild.class);
			for (TbItemChild tbItemChild : list) {
				if(tbItemChild.getId() == id) {
					tbItemChild.setNum(num);
				}
			}
		}
		EgoResult er = new EgoResult();
		String ok = jedisDaoImpl.set(key, JsonUtils.objectToJson(list));
		if(ok.equals("OK")) {
			er.setStatus(200);
		}
		return er;
	}

}
