package com.ego.passport.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ego.commons.pojo.EgoResult;
import com.ego.passport.service.TbUserService;
import com.ego.pojo.TbUser;

@Controller
public class TbUserController {
	
	@Resource
	private TbUserService tbUserService;
	//查登录页面
	@RequestMapping("user/showLogin")
	public String login(){
		System.out.println("login()");
		return "login";
	}
	//登录功能
		@RequestMapping("user/login")
		@ResponseBody
		public EgoResult login(TbUser tbuser,HttpServletRequest request,HttpServletResponse response){
			//需要业务逻辑层的“登录方法”
			return tbUserService.login(tbuser,request,response);
		}
		
		@RequestMapping("user/token/{token}")
		@ResponseBody
		public Object getUserInfo(@PathVariable String token,String callback){
			//需要调用业务层的方法，返回EgoResult对象
			EgoResult er = tbUserService.getUserInfo(token);
			if(callback!=null && !callback.equals("")){
				MappingJacksonValue mjv = new MappingJacksonValue(er);
				mjv.setJsonpFunction(callback);
				return mjv;
			}
			return er;
		}
		/**
		 * 退出功能
		 * @param token
		 * @param callback
		 * @param request
		 * @param response
		 * @return
		 */
		@RequestMapping("user/logout/{token}")
		@ResponseBody
		public Object logout(@PathVariable String token,String callback,HttpServletRequest request,HttpServletResponse response){
			//1.需要调用业务逻辑的“退出”方法
			EgoResult er = tbUserService.logout(token, request, response);
			//2.判断callback是否有值，如果有值则以jsonp的方式返回
			if(callback!=null && !callback.equals("")){
				MappingJacksonValue mjv = new MappingJacksonValue(er);
				mjv.setJsonpFunction(callback);
				return mjv;
			}
			return er;
		}
}
