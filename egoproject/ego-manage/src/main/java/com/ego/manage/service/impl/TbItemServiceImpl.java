package com.ego.manage.service.impl;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ego.commons.pojo.EasyUIDataGrid;
import com.ego.commons.utils.IDUtils;
import com.ego.dubbo.service.TbItemDubboService;
import com.ego.manage.service.TbItemService;
import com.ego.pojo.TbItem;
import com.ego.pojo.TbItemDesc;
import com.ego.pojo.TbItemParamItem;

@Service
public class TbItemServiceImpl implements TbItemService{

	//拿到提供者中接口的对象
	@Reference
	private TbItemDubboService tbItemDubboService;
	@Override
	public EasyUIDataGrid show(int page, int rows) {
		return tbItemDubboService.show(page, rows);
	}
	// TODO .................
	@Override
	public int updateTbItem(String ids, byte status) {
		
		int index = 0;
		TbItem tbItem = new TbItem();
		String[] idsStr = ids.split(",");
		for (String id : idsStr) {
			tbItem.setId(Long.parseLong(id));
			tbItem.setStatus(status);
			index +=tbItemDubboService.updItemStatus(tbItem);
		}
		if(index == idsStr.length ){
			return 1;
		}
		return 0;
	}
	//新增商品
	@Override
	public int saveInfo(TbItem tbItem, String desc,String itemParams) throws Exception {
		//1.先完善“商品表实体类”的信息
		//1.1要人为的添加id信息
		long id = IDUtils.genItemId();
		tbItem.setId(id);
		//1.2需要添加商品状态
		tbItem.setStatus((byte)1);
		//1.3需要加上创建时间
		Date date = new Date();
		tbItem.setCreated(date);
		//1.4需要加上更新时间
		tbItem.setUpdated(date);
		//2.再完善“商品描述表实体类”的信息
		TbItemDesc tbItemDesc = new TbItemDesc();
		tbItemDesc.setItemId(id);
		tbItemDesc.setItemDesc(desc);
		tbItemDesc.setCreated(date);
		tbItemDesc.setUpdated(date);
		//添加“商品规格”中的参数
		TbItemParamItem paramItem = new TbItemParamItem();
		paramItem.setCreated(date);
		paramItem.setUpdated(date);
		paramItem.setItemId(id);
		paramItem.setParamData(itemParams);
		int index = tbItemDubboService.insTbItemInfo(tbItem, tbItemDesc,paramItem);
		return index;
	}

}
