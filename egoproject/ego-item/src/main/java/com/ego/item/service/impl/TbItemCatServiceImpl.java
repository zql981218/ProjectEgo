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
	//�õ��ṩ�߷����Ľӿ�
	@Reference
	private TbItemCatDubboService tbItemCatDubboService;
	
	@Override
	public PortalMenu showItemCat() {
		List<TbItemCat> list = tbItemCatDubboService.showTbItemCat(0);
		PortalMenu pm = new PortalMenu();
		pm.setData(selAll(list));
		return pm;
	}
	
	//�ݹ飺�Լ������Լ���������Ҳ�ѯ
	private List<Object> selAll(List<TbItemCat> list) {
		List<Object> listObj = new ArrayList<>();
		for (TbItemCat cat : list) {
			if(cat.getIsParent()){
				//�ٰ��Ӳ˵������ݴ�ŵ�PortalMenuNode
				PortalMenuNode pmd = new PortalMenuNode();
				pmd.setU("/products/"+cat.getId()+".html");
				pmd.setN("<a href='/products/"+cat.getId()+".html'>"+cat.getName()+"</a>");
				//�˴���Ҫ���ҵ���
				pmd.setI(selAll(tbItemCatDubboService.showTbItemCat(cat.getId())));
				listObj.add(pmd);
			}else{
				//�Ӳ˵�,��iΪ�Ӳ˵�ʱ��ֱ�ӷ����Լ�������Ҫ�ݹ�
				listObj.add("/products/"+cat.getId()+".html|"+cat.getName());
			}
		}
		return listObj;
	}

}
