package com.ego.portal.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ego.portal.service.TbContentService;

@Controller
public class TbContentController {
		
	//拿到业务逻辑层的接口对象
		@Resource
		private TbContentService tbContentService;
		
		@RequestMapping("showImage")
		public String showImage(Model model){
			model.addAttribute("ad1", tbContentService.showImage());
			return "index";
		}
}
