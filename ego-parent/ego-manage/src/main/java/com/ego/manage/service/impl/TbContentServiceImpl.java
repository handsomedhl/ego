package com.ego.manage.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ego.commons.pojo.EasyUIDataGrid;
import com.ego.commons.pojo.EgoResult;
import com.ego.commons.utils.JsonUtils;
import com.ego.dubbo.service.TbContentDubboService;
import com.ego.manage.service.TbContentService;
import com.ego.pojo.TbContent;
import com.ego.redis.dao.JedisDao;

@Service
public class TbContentServiceImpl implements TbContentService {
	@Reference
	private TbContentDubboService tbContentDubboService;
	@Value("${redis.bigpic.key}")
	private String key;
	@Resource
	private JedisDao jedisDaoImpl;

	@Override
	public EasyUIDataGrid selByCategoryId(long categoryId, int page, int rows) {
		return tbContentDubboService.selByCategoryId(categoryId, page, rows);
	}

	@Override
	public EgoResult insTbContent(TbContent content) {
		EgoResult er = new EgoResult();
		Date date = new Date();
		content.setCreated(date);
		content.setUpdated(date);
		int index = tbContentDubboService.insTbContent(content);
		if (index > 0) {
			er.setStatus(200);
			// 后台新增时将数据同步到redis中
			if(jedisDaoImpl.exists(key)) {
				String string = jedisDaoImpl.get(key);
				if(string != null && !string.equals("")) {
					List<HashMap> list = JsonUtils.jsonToList(string, HashMap.class);
					HashMap<String, Object> map = new HashMap<>();
					map.put("srcB", content.getPic2());
					map.put("height", 240);
					map.put("alt", "对不起,加载图片失败");
					map.put("width", 670);
					map.put("src", content.getPic());
					map.put("widthB", 550);
					map.put("href", content.getUrl());
					map.put("heightB", 240);
					
					//保证6个
					if(list.size() == 6) {
						list.remove(5);
					}
					list.add(0, map);;
					String data = JsonUtils.objectToJson(list);
					jedisDaoImpl.set(key, data);
				}
			}
		
		}
		return er;
	}
}
