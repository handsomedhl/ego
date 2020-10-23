package com.ego.passport.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ego.commons.pojo.EgoResult;
import com.ego.pojo.TbUser;

public interface TbUserService {
	/**
	 * 用户登录
	 * @param user
	 * @param request
	 * @param response
	 * @return
	 */
	EgoResult login(TbUser user,HttpServletRequest request,HttpServletResponse response);
	/**
	 * 检查用户登录
	 * @param token
	 * @return
	 */
	EgoResult checkLogin(String token);
	/**
	 * 退出登录
	 * @param token
	 * @return
	 */
	EgoResult loginOut(String token,HttpServletRequest request,HttpServletResponse response);
}
