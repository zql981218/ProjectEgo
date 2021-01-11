package com.ego.manage.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ego.commons.pojo.EasyUiTree;
import com.ego.commons.pojo.EgoResult;
import com.ego.manage.service.TbContentCategoryServie;
import com.ego.pojo.TbContentCategory;

@Controller
public class TbContentCategoryController {

	//拿到业务逻辑的接口对象
	@Resource
	private TbContentCategoryServie tbContentCategoryServie;
	@RequestMapping("content/category/list")
	@ResponseBody
	public List<EasyUiTree> showMenu(@RequestParam(defaultValue="0") long id){
		//需要调用业务逻辑层的方法
		return tbContentCategoryServie.selByPid(id);
	}
	
	//新增
	@RequestMapping("content/category/create")
	@ResponseBody
	public EgoResult create(TbContentCategory cate){
		return tbContentCategoryServie.create(cate);
	}
	
	//修改
	@RequestMapping("content/category/update")
	@ResponseBody
	public EgoResult updateInfo(TbContentCategory cate){
		//调用业务逻辑层的“修改”方法
		return tbContentCategoryServie.update(cate);
	}
	
	//逻辑删除
	@RequestMapping("content/category/delete")
	@ResponseBody
	public EgoResult deleteInfo(TbContentCategory cate){
		//调用业务逻辑层的“删除”方法
		return tbContentCategoryServie.delete(cate);
	}
	
	
}
