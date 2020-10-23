package com.ego.manage.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ego.commons.pojo.EasyUIDataGrid;
import com.ego.commons.utils.HttpClientUtil;
import com.ego.commons.utils.IDUtils;
import com.ego.commons.utils.JsonUtils;
import com.ego.dubbo.service.TbItemDescDubboService;
import com.ego.dubbo.service.TbItemDubboService;
import com.ego.manage.service.TbItemService;
import com.ego.pojo.TbItem;
import com.ego.pojo.TbItemDesc;
import com.ego.pojo.TbItemParamItem;
import com.ego.redis.dao.JedisDao;
/**
 * service中一般处理异常时为了避免屏蔽spring的事务处理
 * @author hl43674824
 *
 */
@Service
public class TbItemServiceImpl implements TbItemService {
	@Reference
	private TbItemDubboService tbItemDubboServiceImpl;
	@Reference
	private TbItemDescDubboService tbItemDescDubboService;
	@Value("${search.url}")
	private String url;
	@Resource
	private JedisDao jedisDaoImpl;
	@Value("${redis.item.key}")
	private String itemKey;
	@Override
	public EasyUIDataGrid show(int page, int rows) {
		return tbItemDubboServiceImpl.show(page, rows);
	}

	@Override
	public int update(String ids, byte status) {
		TbItem item = new TbItem();
		String[] split = ids.split(",");
		int index = 0;
		for (String id : split) {
			item.setId(Long.parseLong(id));
			item.setStatus(status);
			tbItemDubboServiceImpl.updateItemStatus(item);
			index += 1;
			if(status == 2 || status == 3) {
				jedisDaoImpl.del(itemKey + id);
			}
		}
		if (index == split.length) {
			return 1;
		}
		return 0;
	}

	@Override
	public int insTbItem(TbItem item, String desc,String itemParams) throws Exception {
		//不考虑事务回滚
//		long id = IDUtils.genItemId();
//		item.setId(id);
//		Date date = new Date();
//		item.setStatus((byte)1);
//		item.setCreated(date);
//		item.setUpdated(date);
//		int index = tbItemDubboServiceImpl.insTbItem(item);
//		if(index > 0) {
//			TbItemDesc itemDesc = new TbItemDesc();
//			itemDesc.setItemId(id);
//			itemDesc.setCreated(date);
//			itemDesc.setUpdated(date);
//			itemDesc.setItemDesc(desc);
//			index += tbItemDescDubboService.insTbItemDesc(itemDesc);
//		}
//		if(index == 2) {
//			return 1;
//		}
//		return 0;
		//考虑事务回滚
		long id = IDUtils.genItemId();
		item.setId(id);
		Date date = new Date();
		item.setStatus((byte)1);
		item.setCreated(date);
		item.setUpdated(date); 	
		
		TbItemDesc itemDesc = new TbItemDesc();
		itemDesc.setItemId(id);
		itemDesc.setCreated(date);
		itemDesc.setUpdated(date);
		itemDesc.setItemDesc(desc);
		
		TbItemParamItem paramItem = new TbItemParamItem();
		paramItem.setParamData(itemParams);
		paramItem.setCreated(date);
		paramItem.setUpdated(date);
		paramItem.setItemId(id);
		
		//将新增商品数据同步到solr中,使用httpClient发送请求给ego-search中的控制器
		new Thread() {
			public void run() {
				Map<String,Object> map = new HashMap<>();
				map.put("item", item);
				map.put("desc", itemDesc);
				HttpClientUtil.doPostJson(url, JsonUtils.objectToJson(map));
			}
		}.start();
		
		
		
		return tbItemDubboServiceImpl.insTbItemDesc(item, itemDesc,paramItem);
	}
}
