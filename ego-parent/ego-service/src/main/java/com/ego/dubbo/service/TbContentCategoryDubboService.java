package com.ego.dubbo.service;

import java.util.List;
import com.ego.pojo.TbContentCategory;

public interface TbContentCategoryDubboService {
	/**
	 * 根据父id查询所有子类目
	 * @param pid
	 * @return
	 */
	List<TbContentCategory> selByPid(long pid);
	/**
	 * 新增类目
	 * @param cate
	 * @return
	 */
	int insCategoryBy(TbContentCategory cate,TbContentCategory parent)  throws Exception ;
	/**
	 * 修改类目信息
	 * @param cate
	 * @return
	 */
	int update(TbContentCategory parent);
	/**
	 * 根据id查询此类目的所有信息
	 * @param cate
	 * @return
	 */
	TbContentCategory selById(long id);
}
