package com.ego.passport.service.impl;

import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ego.commons.pojo.EgoResult;
import com.ego.commons.utils.CookieUtils;
import com.ego.commons.utils.JsonUtils;
import com.ego.dubbo.service.TbUserDubboService;
import com.ego.passport.service.TbUserService;
import com.ego.pojo.TbUser;
import com.ego.redis.dao.JedisDao;

@Service
public class TbUserServiceImpl implements TbUserService{
	@Reference
	private TbUserDubboService tbUserDubboService;
	@Resource
	private JedisDao jedisDaoImpl;
	@Override
	public EgoResult login(TbUser user,HttpServletRequest request,HttpServletResponse response) {
		EgoResult er = new EgoResult();
		TbUser userSelect = tbUserDubboService.selByUser(user);
		if(userSelect!=null) {
			er.setStatus(200);
			er.setMsg("OK");
			//将用户信息存储到redis中
			String key = UUID.randomUUID().toString();
			String value = JsonUtils.objectToJson(userSelect);
			jedisDaoImpl.set(key, value);
			jedisDaoImpl.expire(key, 60*60*24*7);
			//将redis的key存储到cookie中
			CookieUtils.setCookie(request, response, "TT_TOKEN", key, 60*60*24*7);
		}else {
			er.setMsg("用户名和密码错误");
		}
		return er;
	}
	@Override
	public EgoResult checkLogin(String token) {
		EgoResult er = new EgoResult();
		if(jedisDaoImpl.exists(token)) {
			String json = jedisDaoImpl.get(token);
			if(json!=null && !json.equals("")) {
				TbUser user = JsonUtils.jsonToPojo(json, TbUser.class);
				er.setStatus(200);
				er.setMsg("OK");
				//把密码清空
				user.setPassword(null);
				er.setData(user);
			}
		}else {
			er.setMsg("获取失败");
		}
		return er;
	}
	@Override
	public EgoResult loginOut(String token,HttpServletRequest request,HttpServletResponse response) {
		EgoResult er = new EgoResult();
		//将redis中的用户信息删除
		jedisDaoImpl.del(token);
		//设置cookie失效
		CookieUtils.setCookie(request, response, "TT_TOKEN", token, -1);
		er.setStatus(200);
		er.setMsg("OK");
		return er;
	}

}
