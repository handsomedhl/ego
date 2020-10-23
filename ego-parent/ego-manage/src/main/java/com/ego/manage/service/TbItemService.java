package com.ego.manage.service;

import com.ego.commons.pojo.EasyUIDataGrid;
import com.ego.pojo.TbItem;
import com.ego.pojo.TbItemParamItem;

public interface TbItemService {
	/**
	 * 分页展示商品信息
	 * @param page
	 * @param rows
	 * @return
	 */
	 EasyUIDataGrid show(int page,int rows);
	 /**
	  * 批量修改商品状态
	  * @param ids
	  * @param status
	  * @return
	  */
	 int update(String ids,byte status);
	 /**
	  * 新增商品信息以及商品描述以及规格参数
	  * @param item
	  * @param desc
	  * @return
	  * @throws Exception
	  */
	 int insTbItem(TbItem item,String desc,String itemParams) throws Exception;
}
