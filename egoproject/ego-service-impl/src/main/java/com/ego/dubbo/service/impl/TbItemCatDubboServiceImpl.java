package com.ego.dubbo.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ego.dubbo.service.TbItemCatDubboService;
import com.ego.mapper.TbItemCatMapper;
import com.ego.pojo.TbItemCat;
import com.ego.pojo.TbItemCatExample;
@Service
public class TbItemCatDubboServiceImpl implements TbItemCatDubboService{
	
	//拿到数据访问的接口对象
	@Resource
	private TbItemCatMapper tbItemCatMapper;
	
	@Override
	public List<TbItemCat> showTbItemCat(long pid) {
		TbItemCatExample example = new TbItemCatExample();
		//需要用父ID进行查询
		example.createCriteria().andParentIdEqualTo(pid);
		return tbItemCatMapper.selectByExample(example);
	}

	@Override
	public TbItemCat selectById(long id) {
		return tbItemCatMapper.selectByPrimaryKey(id);
	}

}
