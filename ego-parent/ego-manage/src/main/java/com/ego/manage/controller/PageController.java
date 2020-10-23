package com.ego.manage.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class PageController {
	/**
	 * 欢迎页
	 * @return
	 */
	@RequestMapping("/")
	public String welcome() {
		return "index";
	}
	/**
	 * 根据请求路径跳转页面
	 * @param page
	 * @return
	 */
	@RequestMapping("{page}")
	public String show(@PathVariable String page) {
		return page;
	}
}
