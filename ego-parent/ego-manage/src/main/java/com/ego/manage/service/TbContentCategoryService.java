package com.ego.manage.service;

import java.util.List;

import com.ego.commons.pojo.EasyUITree;
import com.ego.commons.pojo.EgoResult;
import com.ego.pojo.TbContentCategory;

public interface TbContentCategoryService {
	/**
	 * 根据父id查询所有子类目
	 * @param pid
	 * @return
	 */
	List<EasyUITree> selByPid(long pid);
	/**
	 *	新增类目，包含对父类目的修改
	 * @param cate
	 * @return
	 */
	EgoResult insCategory(TbContentCategory cate);
	/**
	 * 根据id重命名
	 * @param id
	 * @return
	 */
	EgoResult update(TbContentCategory cate);
	/**
	 * 删除类目信息
	 * @param cate
	 * @return
	 */
	EgoResult delete(TbContentCategory cate);
	
}
