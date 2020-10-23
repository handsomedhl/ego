package com.ego.item.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ego.commons.pojo.TbItemChild;
import com.ego.item.service.TbItemService;

@Controller
public class TbItemController {
	@Resource
	private TbItemService tbItemServiceImpl;
	/**
	 * 根据id展示商品具体信息
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping("item/{id}.html")
	public String show(Model model,@PathVariable long id) {
		TbItemChild itemChild = tbItemServiceImpl.show(id);
		model.addAttribute("item",itemChild);
		return "item";
	}
}
