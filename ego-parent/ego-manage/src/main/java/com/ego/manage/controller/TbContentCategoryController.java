package com.ego.manage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ego.commons.pojo.EasyUITree;
import com.ego.commons.pojo.EgoResult;
import com.ego.manage.service.TbContentCategoryService;
import com.ego.pojo.TbContentCategory;

@Controller
public class TbContentCategoryController {
	@Autowired
	private TbContentCategoryService tbContentCategoryService;
	/**
	 * 显示类目信息
	 * @param id
	 * @return
	 */
	@RequestMapping("content/category/list")
	@ResponseBody
	public List<EasyUITree> showCategory(@RequestParam(defaultValue = "0")long id){
		return tbContentCategoryService.selByPid(id);
	}
	/**
	 * 新增类目，包含修改父类目状态
	 * @param cate
	 * @return
	 */
	@RequestMapping("content/category/create")
	@ResponseBody
	public EgoResult insCategory(TbContentCategory cate) {
		return tbContentCategoryService.insCategory(cate);
	}
	/**
	 * 修改类目信息
	 * @param cate
	 * @return
	 */
	@RequestMapping("content/category/update")
	@ResponseBody
	public EgoResult update(TbContentCategory cate) {
		return tbContentCategoryService.update(cate);
	}
	/**
	 * 删除类目信息，包含对父节点的修改
	 * @param cate
	 * @return
	 */
	@RequestMapping("content/category/delete")
	@ResponseBody
	public EgoResult delete(TbContentCategory cate) {
		return tbContentCategoryService.delete(cate);
	}
}
