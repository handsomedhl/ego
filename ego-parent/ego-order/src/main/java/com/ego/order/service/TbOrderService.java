package com.ego.order.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ego.commons.pojo.EgoResult;
import com.ego.commons.pojo.TbItemChild;
import com.ego.order.pojo.MyOrderParam;

public interface TbOrderService {
	/**
	 * 显示订单页面
	 * @param ids
	 * @param request
	 * @return
	 */
	List<TbItemChild> showOrder(List<Long> ids,HttpServletRequest request);
	/**
	 * 创建订单
	 * @param param
	 * @param request
	 * @return
	 */
	EgoResult create(MyOrderParam param,HttpServletRequest request);
}
