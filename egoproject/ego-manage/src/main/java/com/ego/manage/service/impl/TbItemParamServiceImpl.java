package com.ego.manage.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ego.commons.pojo.EasyUIDataGrid;
import com.ego.commons.pojo.EgoResult;
import com.ego.dubbo.service.TbItemCatDubboService;
import com.ego.dubbo.service.TbItemParamDubboService;
import com.ego.manage.pojo.TbItemParamChild;
import com.ego.manage.service.TbItemParamService;
import com.ego.pojo.TbItemCat;
import com.ego.pojo.TbItemParam;

@Service
public class TbItemParamServiceImpl implements TbItemParamService{

	//引用提供者中接口对象
	@Reference
	private TbItemParamDubboService tbItemParamDubboService;
	
	@Reference
	private TbItemCatDubboService tbItemCatDubboService;
	
	@Override
	public EasyUIDataGrid showPage(int page, int rows) {
		EasyUIDataGrid datagrid = tbItemParamDubboService.showPage(page, rows);
		List<TbItemParam> list = (List<TbItemParam>) datagrid.getRows();
		List<TbItemParamChild> childList = new ArrayList<>();
		for (TbItemParam param : list) {
			//需要将TbItemParam中的值，重新赋给TbItemParamChild类
			TbItemParamChild child = new TbItemParamChild();
			
			child.setId(param.getId());
			Long itemCatId = param.getItemCatId();
			child.setItemCatId(itemCatId);
			child.setCreated(param.getCreated());
			child.setUpdated(param.getUpdated());
			child.setParamData(param.getParamData());
			TbItemCat tbitemcat = tbItemCatDubboService.selectById(itemCatId);
			child.setItemCatName(tbitemcat.getName());
			childList.add(child);
		}
		datagrid.setRows(childList);
		return datagrid;
	}

	@Override
	public int delete(String ids) throws Exception {
		return tbItemParamDubboService.delById(ids);
	}

	@Override
	public EgoResult selectById(long catid) {
		EgoResult er = new EgoResult();
		//调用提供者提供的用ID查询的方法
		TbItemParam tbitemparam = tbItemParamDubboService.selById(catid);
		if(tbitemparam!=null){
			er.setStatus(200);
			er.setData(tbitemparam);
		}
		return er;
	}

	@Override
	public EgoResult insertItemParam(TbItemParam tbitemparam) {
		Date date = new Date();
		tbitemparam.setCreated(date);
		tbitemparam.setUpdated(date);
		int index = tbItemParamDubboService.insItemParam(tbitemparam);
		EgoResult er = new EgoResult();
		if(index>0){
			er.setStatus(200);	
		}
		return er;
	}
	
	

}
