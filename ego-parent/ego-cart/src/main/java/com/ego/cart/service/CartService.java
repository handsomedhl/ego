package com.ego.cart.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ego.commons.pojo.EgoResult;
import com.ego.commons.pojo.TbItemChild;

public interface CartService {
	/**
	 * 添加商品到购物车
	 * @param id
	 * @param num
	 * @param request
	 */
	void addCart(long id, int num, HttpServletRequest request);
	/**
	 * 显示购物车界面
	 * @param request
	 * @return
	 */
	List<TbItemChild> show(HttpServletRequest request);
	/**
	 * 删除购物车中的商品通过id
	 * @param id
	 * @param request
	 * @return
	 */
	EgoResult deleteById(long id, HttpServletRequest request);
	/**
	 * 修改商品数目
	 * @param id
	 * @param num
	 * @return
	 */
	EgoResult update(long id, int num, HttpServletRequest request);
}
