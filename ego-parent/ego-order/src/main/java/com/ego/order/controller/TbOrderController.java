package com.ego.order.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ego.order.pojo.MyOrderParam;
import com.ego.order.service.TbOrderService;

@Controller
public class TbOrderController {
	@Resource
	private TbOrderService tbOrderServiceImpl;
	/**
	 * 显示订单页面信息
	 * @param page
	 * @param ids
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("order/order-cart.html")
	public String showPage(String page,@RequestParam("id") List<Long> ids,Model model,HttpServletRequest request) {
		model.addAttribute("cartList",tbOrderServiceImpl.showOrder(ids, request));
		return "order-cart";
	}
	/**
	 * 创建订单
	 * @param param
	 * @param request
	 * @return
	 */
	@RequestMapping("order/create.html")
	public String create(MyOrderParam param,HttpServletRequest request) {
		tbOrderServiceImpl.create(param, request);
		return "my-orders";
	}
}
