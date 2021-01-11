package com.ego.passport.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ego.commons.pojo.EgoResult;
import com.ego.pojo.TbUser;

public interface TbUserService {
	
	/**
	 * µÇÂ¼¹¦ÄÜ
	 * @param tbuser
	 * @return
	 */
	public EgoResult login(TbUser tbuser,HttpServletRequest request,HttpServletResponse response);
	
	public EgoResult getUserInfo(String token);
	
	/**
	 * ÍË³ö
	 * @param token
	 * @param request
	 * @param response
	 * @return
	 */
	public EgoResult logout(String token,HttpServletRequest request,HttpServletResponse response);
}
