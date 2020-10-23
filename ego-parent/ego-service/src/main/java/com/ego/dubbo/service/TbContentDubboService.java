package com.ego.dubbo.service;

import java.util.List;

import com.ego.commons.pojo.EasyUIDataGrid;
import com.ego.pojo.TbContent;

public interface TbContentDubboService {
	/**
	 * 根据类目id查询内容
	 * @param catagoryId
	 * @param page
	 * @param rows
	 * @return
	 */
	EasyUIDataGrid selByCategoryId(long catagoryId,int page,int rows);
	/**
	 * 内容新增
	 * @param content
	 * @return
	 */
	int insTbContent(TbContent content);
	/**
	 * 查询出前n个内容
	 * @param count
	 * @param isSort 是否将其排序
	 * @return
	 */
	List<TbContent> selByCount(int count,boolean isSort);
}
