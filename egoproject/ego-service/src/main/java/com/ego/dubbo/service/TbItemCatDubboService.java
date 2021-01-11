package com.ego.dubbo.service;

import java.util.List;

import com.ego.pojo.TbItemCat;

/**
 * 显示“商品类目”的接口
 * @author Administrator
 *
 */
public interface TbItemCatDubboService {

	//通过父Id查询子项目（包含自我查询）
	List<TbItemCat> showTbItemCat(long pid);
	//通过ID查询商品类目信息的方法
	public TbItemCat selectById(long id);
}
