package com.ego.manage.service;

import com.ego.commons.pojo.EasyUIDataGrid;
import com.ego.commons.pojo.EgoResult;
import com.ego.pojo.TbItemCat;
import com.ego.pojo.TbItemParam;

public interface TbItemParamService {
	
	
	/**
	 * 查询商品规格参数信息 
	 * @param page
	 * @param rows
	 * @return
	 */
	EasyUIDataGrid showPage(int page,int rows);
	
	int delete(String ids) throws Exception;
	
	/**
	 * 查询(用ID查询)
	 * @param catid
	 * @return
	 */
	public EgoResult selectById(long catid);
	
	/**
	 * 增加
	 */
	public EgoResult insertItemParam(TbItemParam tbitemparam);
}
