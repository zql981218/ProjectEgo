package com.ego.dubbo.service;

import com.ego.pojo.TbUser;

public interface TbUserDubboService {
	
	//登录(本质为查询)
	public TbUser selectByUser(TbUser tbuser);
}
