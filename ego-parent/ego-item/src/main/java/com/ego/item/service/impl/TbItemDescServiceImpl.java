package com.ego.item.service.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ego.dubbo.service.TbItemDescDubboService;
import com.ego.item.service.TbItemDescService;
import com.ego.redis.dao.JedisDao;
@Service
public class TbItemDescServiceImpl implements TbItemDescService {
	@Reference
	private TbItemDescDubboService tbItemDescDubboService;
	@Resource
	private JedisDao jedisDaoImpl;
	@Value("${redis.desc.key}")
	private String descKey;
	@Override
	public String showDesc(long id) {
		String key = descKey + id;
		if(jedisDaoImpl.exists(key)) {
			String json = jedisDaoImpl.get(key);
			if(json != null && !json.equals("")) {
				return json;
			}
		}
		String result = tbItemDescDubboService.selById(id).getItemDesc();
		new Thread() {
			public void run() {
				jedisDaoImpl.set(key, result);
			}
		}.start();
		return result;
	}

}
