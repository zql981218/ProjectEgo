package com.ego.manage.service;

import com.ego.commons.pojo.EasyUIDataGrid;
import com.ego.pojo.TbItem;

public interface TbItemService {
	/**
	 * 显示商品
	 * @param page
	 * @param rows
	 * @return
	 */
	EasyUIDataGrid show(int page,int rows);
	
	/**
	 * 修改：上架+下架+删除
	 * @param ids
	 * @param status
	 * @return
	 */
	int updateTbItem(String ids,byte status);
	
	//新增商品
	int saveInfo(TbItem tbItem,String desc,String itemParams) throws Exception;
}
