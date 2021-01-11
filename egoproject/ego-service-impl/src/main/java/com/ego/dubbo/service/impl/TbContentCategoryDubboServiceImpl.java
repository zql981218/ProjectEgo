package com.ego.dubbo.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ego.dubbo.service.TbContentCategoryDubboService;
import com.ego.mapper.TbContentCategoryMapper;
import com.ego.pojo.TbContentCategory;
import com.ego.pojo.TbContentCategoryExample;

@Service
public class TbContentCategoryDubboServiceImpl implements TbContentCategoryDubboService{
	
	//拿到数据访问层的接口对象
		@Resource
		private TbContentCategoryMapper tbContentCategoryMapper;
	
	@Override
	public List<TbContentCategory> selectByPid(long id) {
		TbContentCategoryExample example = new TbContentCategoryExample();
		example.createCriteria().andParentIdEqualTo(id).andStatusEqualTo(1);
		return tbContentCategoryMapper.selectByExample(example);
	}

	@Override
	public int insTbContentCategory(TbContentCategory cate) {
		return tbContentCategoryMapper.insertSelective(cate);
	}

	@Override
	public int updTbContentCategory(TbContentCategory cate) {
		//当列的值为null，就不会修改
		return tbContentCategoryMapper.updateByPrimaryKeySelective(cate);
	}

	@Override
	public TbContentCategory selById(long id) {
		return tbContentCategoryMapper.selectByPrimaryKey(id);
	}

}
