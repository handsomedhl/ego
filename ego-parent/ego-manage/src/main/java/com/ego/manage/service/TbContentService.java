package com.ego.manage.service;

import com.ego.commons.pojo.EasyUIDataGrid;
import com.ego.commons.pojo.EgoResult;
import com.ego.pojo.TbContent;

public interface TbContentService {
	/**
	 * 根据类目id进行查询
	 * @param categoryId
	 * @param page
	 * @param rows
	 * @return
	 */
	EasyUIDataGrid selByCategoryId(long categoryId,int page,int rows);
	/**
	 * 新增内容
	 * @param content
	 * @return
	 */
	EgoResult insTbContent(TbContent content);
}
