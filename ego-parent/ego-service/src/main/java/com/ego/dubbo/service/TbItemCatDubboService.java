package com.ego.dubbo.service;

import java.util.List;

import com.ego.pojo.TbItemCat;

public interface TbItemCatDubboService {
	/**
	 * 显示当前父节点的所有孩子结点信息
	 * @param pid
	 * @return
	 */
	List<TbItemCat> show(long pid);
	/**
	 * 根据id查询商品类目
	 * @param id
	 * @return
	 */
	TbItemCat selById(long id);
}
