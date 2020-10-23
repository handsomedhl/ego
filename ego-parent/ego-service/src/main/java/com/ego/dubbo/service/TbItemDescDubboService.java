package com.ego.dubbo.service;

import com.ego.pojo.TbItemDesc;

public interface TbItemDescDubboService {
	/**
	 * 商品描述新增
	 * @param itemDesc
	 * @return
	 */
	int insTbItemDesc(TbItemDesc itemDesc);
	/**
	 * 根据商品id查询商品描述
	 * @param itemid
	 * @return
	 */
	TbItemDesc selById(long itemid);
}
