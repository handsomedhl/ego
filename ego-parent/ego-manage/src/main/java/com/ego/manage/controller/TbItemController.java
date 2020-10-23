package com.ego.manage.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ego.commons.pojo.EasyUIDataGrid;
import com.ego.commons.pojo.EgoResult;
import com.ego.manage.service.TbItemService;
import com.ego.pojo.TbItem;
@Controller
public class TbItemController {
	@Resource
	private TbItemService tbItemServiceImpl;
	
	
	@RequestMapping("item/list")
	@ResponseBody
	public EasyUIDataGrid show(int page,int rows) {
		return tbItemServiceImpl.show(page, rows);
	}
	
	@RequestMapping("rest/page/item-edit")
	public String showItemEdit() {
		return "item-edit";
	}

	/**
	 * 商品删除
	 * @param ids
	 * @return
	 */
	@RequestMapping("rest/item/delete")
	@ResponseBody
	public EgoResult delete(String ids) {
		int index = tbItemServiceImpl.update(ids, (byte)3);
		EgoResult res = new EgoResult();
		if(index > 0) {
			res.setStatus(200);
		}
		return res;
	}
	/**
	 * 商品下架
	 * @param ids
	 * @return
	 */
	@RequestMapping("rest/item/instock")
	@ResponseBody
	public EgoResult instock(String ids) {
		int index = tbItemServiceImpl.update(ids, (byte)2);
		EgoResult res = new EgoResult();
		if(index > 0) {
			System.out.println(index);
			res.setStatus(200);
		}
		return res;
	}
	/**
	 * 商品上架
	 * @param ids
	 * @return
	 */
	@RequestMapping("/rest/item/reshelf")
	@ResponseBody
	public EgoResult reshelf(String ids) {
		int index = tbItemServiceImpl.update(ids, (byte)1);
		EgoResult res = new EgoResult();
		if(index > 0) {
			res.setStatus(200);
		}
		return res;
	}
	/**
	 * 新增商品，包括商品信息，描述以及规格参数
	 * @param item
	 * @param desc
	 * @param itemParams
	 * @return
	 */
	@RequestMapping("item/save")
	@ResponseBody
	public EgoResult save(TbItem item,String desc,String itemParams) {
		EgoResult result = new EgoResult();
		int index = 0;
		try {
			index = tbItemServiceImpl.insTbItem(item, desc,itemParams);
			if(index > 0) {
				result.setStatus(200);
			}
		} catch (Exception e) {
			result.setData(e.getMessage());
			e.printStackTrace();
		}
		return result;
	}
}
