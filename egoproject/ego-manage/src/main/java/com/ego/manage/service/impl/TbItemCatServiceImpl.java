package com.ego.manage.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ego.commons.pojo.EasyUiTree;
import com.ego.dubbo.service.TbItemCatDubboService;
import com.ego.manage.service.TbItemCatService;
import com.ego.pojo.TbItemCat;

@Service
public class TbItemCatServiceImpl implements TbItemCatService{

	//拿到提供者的业务逻辑层的接口对象
	@Reference
	private TbItemCatDubboService tbItemCatDubboService;
	
	@Override
	public List<EasyUiTree> showMenu(long pid) {
		List<TbItemCat> list = tbItemCatDubboService.showTbItemCat(pid);
		List<EasyUiTree> treeList = new ArrayList<>();
		for (TbItemCat cat : list) {
			EasyUiTree tree = new EasyUiTree();
			tree.setId(cat.getId());
			tree.setText(cat.getName());
			tree.setState(cat.getIsParent()?"closed":"open");
			//将回显数据存放到list集合中
			treeList.add(tree);
		}
		return treeList;
	}

}
