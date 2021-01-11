package com.ego.dubbo.service;

import com.ego.commons.pojo.EasyUIDataGrid;
import com.ego.pojo.TbItemParam;

public interface TbItemParamDubboService {

	
	public EasyUIDataGrid showPage(int page, int rows);
	
	/**
	 * 通过Id删除商品规则参数表中的信息
	 * @param id
	 * @return
	 * @throws Exception 
	 */
	public int delById(String ids) throws Exception;
	
	/**
	 * 通过Id查询类目信息
	 * @param catid
	 * @return
	 */
	public TbItemParam selById(long catid);
	
	/**
	 * 增加
	 */
	public int insItemParam(TbItemParam tbitemparam);
}
