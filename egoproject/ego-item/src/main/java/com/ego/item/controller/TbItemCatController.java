package com.ego.item.controller;

import javax.annotation.Resource;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ego.item.pojo.PortalMenu;
import com.ego.item.service.TbItemCatService;

@Controller
public class TbItemCatController {
	
	
	//�õ�ҵ���߼���Ľӿڶ���
	@Resource
	private TbItemCatService tbItemCatService;
	@RequestMapping("rest/itemcat/all")
	@ResponseBody
	public MappingJacksonValue showMenu(String callback){
		//��Ҫһ�����õ�������ݶ���ķ������߶���
		PortalMenu pm = tbItemCatService.showItemCat();
		MappingJacksonValue mjv = new MappingJacksonValue(pm);
		mjv.setJsonpFunction(callback);
		return mjv;
	}
	
}
