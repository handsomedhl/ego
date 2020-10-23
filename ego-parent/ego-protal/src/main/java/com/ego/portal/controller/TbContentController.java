package com.ego.portal.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ego.portal.service.TbContentService;

@Controller
public class TbContentController {
	@Resource
	private TbContentService tbContentServiceImpl;
	/**
	 * 将数据通过reques作用域传递给jsp,显示大广告
	 * @param model
	 * @return
	 */
	@RequestMapping("/showBigPic")
	public String showBigPic(Model model) {
		model.addAttribute("ad1", tbContentServiceImpl.selByCount());
		return "index";
	}
}
