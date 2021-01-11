package com.ego.manage.service;

import com.ego.commons.pojo.EasyUIDataGrid;
import com.ego.pojo.TbContent;

public interface TbContentService {
	
	//查询
	public EasyUIDataGrid selectContent(long categoryId,int page,int rows);
	//增加
	public int save(TbContent tbcontent);
}
