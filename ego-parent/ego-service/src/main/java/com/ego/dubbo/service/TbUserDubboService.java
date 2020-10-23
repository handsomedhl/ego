package com.ego.dubbo.service;

import com.ego.pojo.TbUser;

public interface TbUserDubboService {
	/**
	 * 用户登录
	 * @param user
	 * @return
	 */
	TbUser selByUser(TbUser user);
}
