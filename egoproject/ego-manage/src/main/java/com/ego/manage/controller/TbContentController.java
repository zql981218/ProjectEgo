package com.ego.manage.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ego.commons.pojo.EasyUIDataGrid;
import com.ego.commons.pojo.EgoResult;
import com.ego.manage.service.TbContentService;
import com.ego.pojo.TbContent;

@Controller
public class TbContentController {
	

	//拿到业务逻辑层的接口对象
	@Resource
	private TbContentService tbContentService;
	
	@RequestMapping("content/query/list")
	@ResponseBody
	public EasyUIDataGrid showContent(long categoryId,int page,int rows){
		//需要业务逻辑层提供的方法
		return tbContentService.selectContent(categoryId, page, rows);
	}
	
	@RequestMapping("content/save")
	@ResponseBody
	public EgoResult save(TbContent tbcontent){
		EgoResult er = new EgoResult();
		//调用业务逻辑层方法
		int index = tbContentService.save(tbcontent);
		if(index>0){
			er.setStatus(200);	
		}
		return er;
	}
}
