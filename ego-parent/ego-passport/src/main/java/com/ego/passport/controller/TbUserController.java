package com.ego.passport.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ego.commons.pojo.EgoResult;
import com.ego.passport.service.TbUserService;
import com.ego.pojo.TbUser;

@Controller
public class TbUserController {
	@Resource
	private TbUserService tbUserServiceImpl;
	/**
	 * 显示登录页面
	 * @return
	 */
	@RequestMapping("user/showLogin")
	public String showLogin(@RequestHeader(value = "referer", defaultValue = "") String url,Model model,String interurl) {
		if(interurl!=null && !interurl.equals("")) {
			model.addAttribute("redirect",interurl);
		}else if(!url.equals("")) {
			model.addAttribute("redirect",url);
		}
		return "login";
	}
	/**
	 * 用户登录
	 * @param user
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("user/login")
	@ResponseBody
	public EgoResult login(TbUser user,HttpServletRequest request,HttpServletResponse response) {
		return tbUserServiceImpl.login(user,request,response);
	}
	/**
	 * 检查用户登录
	 * @param token
	 * @param callback
	 * @return
	 */
	@RequestMapping("user/token/{token}")
	@ResponseBody
	public Object getUserInfo(@PathVariable String token,String callback) {
		EgoResult er = tbUserServiceImpl.checkLogin(token);
		if(callback != null && !callback.equals("")) {
			MappingJacksonValue mjv = new MappingJacksonValue(er);
			mjv.setJsonpFunction(callback);
			return mjv;
		}
		return er;
	}
	/**
	 * 退出
	 * @param token
	 * @param callback
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("user/logout/{token}")
	@ResponseBody
	public Object loginOut(@PathVariable String token, String callback,HttpServletRequest request,HttpServletResponse response) {
		EgoResult er = tbUserServiceImpl.loginOut(token, request, response);
		if(callback != null && !callback.equals("")) {
			MappingJacksonValue mjv = new MappingJacksonValue(er);
			mjv.setJsonpFunction(callback);
			return mjv;
		}
		return er;
	}
}
