package com.ego.dubbo.service;

import com.ego.commons.pojo.EasyUIDataGrid;
import com.ego.pojo.TbItem;
import com.ego.pojo.TbItemDesc;
import com.ego.pojo.TbItemParamItem;

public interface TbItemDubboService {
	//增加
	
	//删除
	
	//修改
	
	int updItemStatus(TbItem tbItem);
	/**
	 * 查询全部
	 * @param page:当前属于第几页
	 * @param rows：显示多少行
	 */
	EasyUIDataGrid show(int page,int rows);
	
	/**
	 * 增加商品和商品描述信息
	 * @param tbItem
	 * @param tbitemdesc
	 * @return
	 * @throws Exception 
	 */
	int insTbItemInfo(TbItem tbItem,TbItemDesc tbitemdesc,TbItemParamItem paramItem) throws Exception;
}
