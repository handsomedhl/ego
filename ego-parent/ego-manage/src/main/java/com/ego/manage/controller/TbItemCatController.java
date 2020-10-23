package com.ego.manage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ego.commons.pojo.EasyUITree;
import com.ego.manage.service.TbItemCatService;
@Controller
public class TbItemCatController {
	@Autowired
	private TbItemCatService tbItemCatService;
	/**
	 * 显示商品类目
	 * @return
	 */
	@RequestMapping("item/cat/list")
	@ResponseBody
	public List<EasyUITree> showItemList(@RequestParam(defaultValue = "0")long id) {
		return tbItemCatService.show(id);
	}
}
