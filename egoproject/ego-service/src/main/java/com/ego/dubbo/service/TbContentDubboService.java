package com.ego.dubbo.service;

import java.util.List;

import com.ego.commons.pojo.EasyUIDataGrid;
import com.ego.pojo.TbContent;

public interface TbContentDubboService {
	
	//查询“内容管理”信息
	public EasyUIDataGrid selContentByPage(long categoryId,int page,int rows);
	
	//增加
	public int insTbContent(TbContent tbcontent);
	
	/**
	 * 显示首页大广告
	 * @param count:显示几张图片(默认6张)
	 * @param isSort:是否排序(降序排列)
	 * @return
	 */
	List<TbContent> selectPic(int count,boolean isSort);
}
