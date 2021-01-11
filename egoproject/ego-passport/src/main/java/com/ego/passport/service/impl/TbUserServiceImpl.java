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
public class TbUserServiceImpl implements TbUserService {
	
	//拿到“提供者”方的接口对象
		@Reference
		private TbUserDubboService tbUserDubboService;
		
		@Resource
		private  JedisDao jedisDao;
		@Override
		public EgoResult login(TbUser tbuser,HttpServletRequest request,HttpServletResponse response) {
			EgoResult er = new EgoResult();
			TbUser userSelect = tbUserDubboService.selectByUser(tbuser);
			if(userSelect!=null){
				er.setStatus(200);
				//将存放在userSelect中的信息，保存在Redis中
				String key = UUID.randomUUID().toString();
				String value = JsonUtils.objectToJson(userSelect);
				//把key和value存放在Redis中
				jedisDao.set(key, value);
				//给Redis中数据设置有效时间
				jedisDao.expire(key, 7*24*60*60);
				//需要将Redis中的数据放在Cookie中
				CookieUtils.setCookie(request, response, "TT_TOKEN", key, 7*24*60*60);
			}else{
				er.setMsg("用户名或密码错误");
			}
			return er;
		}
		@Override
		public EgoResult getUserInfo(String token) {
			EgoResult er = new EgoResult();
			String value = jedisDao.get(token);
			if(value!=null && !value.equals("")){
				TbUser tbuser = JsonUtils.jsonToPojo(value, TbUser.class);
				
				er.setStatus(200);
				er.setMsg("OK");
				er.setData(tbuser);
			}else{
				er.setMsg("获取失败！");
			}
			
			return er;
		}
		
		
		@Override
		public EgoResult logout(String token, HttpServletRequest request,
				HttpServletResponse response) {
			//1.删除Redis中数据
			jedisDao.del(token);
			//2.删除Cookie中数据
			CookieUtils.deleteCookie(request, response, token);
			//3.返回状态
			EgoResult er = new EgoResult();
			er.setStatus(200);
			er.setMsg("OK");
			return er;
		}
}
