package com.ego.manage.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ego.commons.pojo.EasyUIDataGrid;
import com.ego.commons.pojo.EgoResult;
import com.ego.manage.service.TbItemParamService;
import com.ego.pojo.TbItemParam;

@Controller
public class TbItemParamController {
	@Resource
	private TbItemParamService tbItemParamServiceImpl;
	/**
	 * 分页显示商品规格参数
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("item/param/list")
	@ResponseBody
	public EasyUIDataGrid showPage(int page,int rows) {
		return tbItemParamServiceImpl.showPage(page, rows);
	}
	/**
	 * 批量删除商品规格参数
	 * @param ids
	 * @return
	 */
	@RequestMapping("item/param/delete")
	@ResponseBody
	public EgoResult delByIds(String ids) {
		return tbItemParamServiceImpl.delByIds(ids);
	}
	/**
	 * 根据商品类目id查询商品规格信息
	 * @param id
	 * @return
	 */
	@RequestMapping("item/param/query/itemcatid/{catId}")
	@ResponseBody
	public EgoResult selByCatId(@PathVariable Long catId) {
		return tbItemParamServiceImpl.selByCatId(catId);
	}
	@RequestMapping("item/param/save/{catId}") 
	@ResponseBody
	public EgoResult save(TbItemParam param,@PathVariable long catId) {
		param.setItemCatId(catId);
		return tbItemParamServiceImpl.insItemParam(param);
	}
}
