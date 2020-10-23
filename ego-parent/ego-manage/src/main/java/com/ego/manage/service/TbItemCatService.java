package com.ego.manage.service;

import java.util.List;

import com.ego.commons.pojo.EasyUITree;

public interface TbItemCatService {
	/**
	 * 显示当前父节点的所有孩子结点的部分信息
	 * @param pid
	 * @return
	 */
	List<EasyUITree> show(long pid);
}
	