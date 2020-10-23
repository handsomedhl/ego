package com.ego.manage.service;

import com.ego.commons.pojo.EasyUIDataGrid;
import com.ego.commons.pojo.EgoResult;
import com.ego.pojo.TbItemParam;

public interface TbItemParamService {
	/**
	 * 分页显示规格参数信息
	 * @param page
	 * @param rows
	 * @return
	 */
	EasyUIDataGrid showPage(int page,int rows);
	/**
	 * 批量删除规格参数
	 * @param ids
	 * @return
	 */
	EgoResult delByIds(String ids);
	/**
	 * 根据商品类目id查询商品规格参数
	 * @param id
	 * @return
	 */
	EgoResult selByCatId(Long id);
	/**
	 * 新增商品规格参数
	 * @param param
	 * @return
	 */
	EgoResult insItemParam(TbItemParam param);
}
