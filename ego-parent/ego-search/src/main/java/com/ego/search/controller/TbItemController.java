package com.ego.search.controller;



import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ego.search.service.TbItemService;

@Controller
public class TbItemController {
	@Resource
	private TbItemService tbItemServiceImpl;
	@RequestMapping(value = "solr/init",produces = "text/html;charset=utf-8")
	@ResponseBody
	public String init() {
		long start = System.currentTimeMillis();
		try {
			tbItemServiceImpl.init();
			long end = System.currentTimeMillis();
			return "初始化总时间："+(end - start) / 1000 + "秒";
		} catch (Exception e) {
			e.printStackTrace();
			return "初始化失败";
		}
	}
	@RequestMapping("search.html")
	public String search(Model model,String q,@RequestParam(defaultValue = "1") int page,@RequestParam(defaultValue = "12") int rows) {
		try {
			q = new String(q.getBytes("iso-8859-1"),"utf-8");
			Map<String, Object> map = tbItemServiceImpl.search(q, page, rows);
			model.addAttribute("query", q);
			model.addAttribute("page", page);
			model.addAttribute("itemList", map.get("itemList"));
			model.addAttribute("totalPages", map.get("totalPages"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "search";
	}
	/**
	 * 同步新增的商品信息到solr中
	 * \@requestBody 将请求体中的数据转换为对应的bean
	 * @param map
	 * @param desc
	 * @return
	 */
	@RequestMapping("solr/add")
	@ResponseBody
	public int add(@RequestBody Map<String,Object> map) {
		int index = 0;
		try {
//			System.out.println(map.get("item"));
//			System.out.println(map.get("desc"));
			index = tbItemServiceImpl.add((Map)map.get("item"), map.get("desc").toString());
			System.out.println("index:"+index);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return index;
	}
}
