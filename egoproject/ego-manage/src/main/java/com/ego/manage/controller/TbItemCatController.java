package com.ego.manage.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ego.commons.pojo.EasyUiTree;
import com.ego.manage.service.TbItemCatService;

@Controller
public class TbItemCatController {
	
	//需要接口对象
	@Resource
	private TbItemCatService tbItemCatService;
	
	//显示菜单
	@RequestMapping("item/cat/list")
	@ResponseBody
	public List<EasyUiTree> showTbItemCat(@RequestParam(defaultValue="0") long id){
		//调用业务逻辑层的查询方法
		return tbItemCatService.showMenu(id);
	}
	
}
