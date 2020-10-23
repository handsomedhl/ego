package com.ego.dubbo.service.impl;

import javax.annotation.Resource;

import com.ego.dubbo.service.TbItemDescDubboService;
import com.ego.mapper.TbItemDescMapper;
import com.ego.pojo.TbItemDesc;

public class TbItemDescDubboServiceImpl implements TbItemDescDubboService {
	@Resource
	private TbItemDescMapper tbItemDescMapper;
	@Override
	public int insTbItemDesc(TbItemDesc itemDesc) {
		return tbItemDescMapper.insert(itemDesc);
	}
	@Override
	public TbItemDesc selById(long itemid) {
		return tbItemDescMapper.selectByPrimaryKey(itemid);
	}

}
