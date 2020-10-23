package com.ego.item.service;

import com.ego.commons.pojo.TbItemChild;

public interface TbItemService {
	/**
	 * 根据id查询商品具体信息
	 * @param id
	 * @return
	 */
	TbItemChild show(long id);
}
