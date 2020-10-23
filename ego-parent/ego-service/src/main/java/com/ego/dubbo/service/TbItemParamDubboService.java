package com.ego.dubbo.service;

import com.ego.commons.pojo.EasyUIDataGrid;
import com.ego.pojo.TbItemParam;

public interface TbItemParamDubboService {
	/**
	 * 分页显示商品规格参数页面
	 * @param page
	 * @param rows
	 * @return
	 */
	EasyUIDataGrid showPage(int page,int rows);
	/**
	 * 批量删除商品规格参数
	 * @param ids
	 * @return
	 */
	int delByIds(String ids) throws Exception;
	
	/**
	 * 根据类目id查询规格参数-验证此类目所对应的模板信息是否存在
	 * @param id
	 * @return
	 */
	TbItemParam selById(long catId);
	/**
	 * 新增商品规格参数
	 * @param param
	 * @return
	 */
	int insItemParam(TbItemParam param);
}
