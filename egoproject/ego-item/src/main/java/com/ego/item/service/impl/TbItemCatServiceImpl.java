package com.ego.item.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ego.dubbo.service.TbItemCatDubboService;
import com.ego.item.pojo.PortalMenu;
import com.ego.item.pojo.PortalMenuNode;
import com.ego.item.service.TbItemCatService;
import com.ego.pojo.TbItemCat;

@Service
public class TbItemCatServiceImpl implements TbItemCatService{
	//拿到提供者方给的接口
	@Reference
	private TbItemCatDubboService tbItemCatDubboService;
	
	@Override
	public PortalMenu showItemCat() {
		List<TbItemCat> list = tbItemCatDubboService.showTbItemCat(0);
		PortalMenu pm = new PortalMenu();
		pm.setData(selAll(list));
		return pm;
	}
	
	//递归：自己调用自己，完成自我查询
	private List<Object> selAll(List<TbItemCat> list) {
		List<Object> listObj = new ArrayList<>();
		for (TbItemCat cat : list) {
			if(cat.getIsParent()){
				//再把子菜单中数据存放到PortalMenuNode
				PortalMenuNode pmd = new PortalMenuNode();
				pmd.setU("/products/"+cat.getId()+".html");
				pmd.setN("<a href='/products/"+cat.getId()+".html'>"+cat.getName()+"</a>");
				//此处需要自我调用
				pmd.setI(selAll(tbItemCatDubboService.showTbItemCat(cat.getId())));
				listObj.add(pmd);
			}else{
				//子菜单,当i为子菜单时，直接返回自己，不需要递归
				listObj.add("/products/"+cat.getId()+".html|"+cat.getName());
			}
		}
		return listObj;
	}

}
