package com.ego.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
	
	//访问前台页面
	@RequestMapping("/")
	public String welcome(){
		return "forward:showImage";
	}
	
}
