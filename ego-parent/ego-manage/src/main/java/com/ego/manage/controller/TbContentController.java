package com.ego.manage.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ego.commons.pojo.EasyUIDataGrid;
import com.ego.commons.pojo.EgoResult;
import com.ego.manage.service.TbContentService;
import com.ego.pojo.TbContent;

@RestController
public class TbContentController {
	@Resource
	private TbContentService tbContentServiceImpl;
	@RequestMapping("content/query/list")
	public EasyUIDataGrid show(long categoryId,int page,int rows) {
		return tbContentServiceImpl.selByCategoryId(categoryId, page, rows);
	}
	@RequestMapping("content/save")
	public EgoResult save(TbContent content) {
		return tbContentServiceImpl.insTbContent(content);
	}
}
	
