package com.ego.item.service.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ego.commons.pojo.TbItemChild;
import com.ego.commons.utils.JsonUtils;
import com.ego.dubbo.service.TbItemDubboService;
import com.ego.item.service.TbItemService;
import com.ego.pojo.TbItem;
import com.ego.redis.dao.JedisDao;
@Service
public class TbItemServiceImpl implements TbItemService {
	@Reference
	private TbItemDubboService tbItemDubboService;
	@Resource
	private JedisDao jedisDaoImpl;
	@Value("${redis.item.key}")
	private String itemKey;
	@Override
	public TbItemChild show(long id) {
		String key = itemKey + id;
		if(jedisDaoImpl.exists(key)) {
			String json = jedisDaoImpl.get(key);
			if(json != null && !json.equals("")) {
				return JsonUtils.jsonToPojo(json, TbItemChild.class);
			}
		}
		TbItem item = tbItemDubboService.selById(id);
		TbItemChild child = new TbItemChild();
		child.setId(id);
		child.setTitle(item.getTitle());
		child.setSellPoint(item.getSellPoint());
		child.setPrice(item.getPrice());
		child.setImages(item.getImage()!=null&&!item.getImage().equals("")?item.getImage().split(","):new String[1]);
		//查询后将数据存储到json中
		new Thread() {
			public void run() {
				jedisDaoImpl.set(key, JsonUtils.objectToJson(child));
			}
		}.start();
		return child;
	}

}
