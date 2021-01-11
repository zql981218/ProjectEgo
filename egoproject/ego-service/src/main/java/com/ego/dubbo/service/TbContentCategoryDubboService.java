package com.ego.dubbo.service;

import java.util.List;

import com.ego.pojo.TbContentCategory;

public interface TbContentCategoryDubboService {
	//通过父Id查询子节点
	public List<TbContentCategory> selectByPid(long id);
	
	//增加节点
	int insTbContentCategory(TbContentCategory cate);
	//修改
	int updTbContentCategory(TbContentCategory cate);
	//通过Id查询对象的方法
	TbContentCategory selById(long id);
}
