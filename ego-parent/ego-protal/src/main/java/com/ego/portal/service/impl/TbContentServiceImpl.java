package com.ego.portal.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ego.commons.utils.JsonUtils;
import com.ego.dubbo.service.TbContentDubboService;
import com.ego.pojo.TbContent;
import com.ego.portal.service.TbContentService;
import com.ego.redis.dao.JedisDao;

import redis.clients.jedis.JedisCluster;

@Service
public class TbContentServiceImpl implements TbContentService{
	@Reference
	private TbContentDubboService tbContentDubboService;
	@Value("${redis.bigpic.key}")
	private String key;
	@Resource
	private JedisDao jedisDaoImpl;
	@Override
	public String selByCount() {
		if(jedisDaoImpl.exists(key)) {
			return jedisDaoImpl.get(key);
		}
		List<Map<String,Object>> result = new ArrayList<>();
		List<TbContent> list = tbContentDubboService.selByCount(6, true);
		for (TbContent tc : list) {
			Map<String,Object> map = new HashMap<>();
			map.put("srcB", tc.getPic2()); 
			map.put("height", 240);
			map.put("alt", "对不起,加载图片失败"); 
			map.put("width", 670); 
			map.put("src", tc.getPic());
			map.put("widthB", 550); 
			map.put("href", tc.getUrl() ); 
			map.put("heightB", 240);
			result.add(map);
		}
		String listJson = JsonUtils.objectToJson(result);
		//第一次查询时，将数据放到redis缓存中
		jedisDaoImpl.set(key, listJson);
		return listJson;
	}
}
