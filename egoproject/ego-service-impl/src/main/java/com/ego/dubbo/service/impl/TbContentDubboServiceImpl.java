package com.ego.dubbo.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ego.commons.pojo.EasyUIDataGrid;
import com.ego.dubbo.service.TbContentDubboService;
import com.ego.mapper.TbContentMapper;
import com.ego.pojo.TbContent;
import com.ego.pojo.TbContentExample;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class TbContentDubboServiceImpl implements TbContentDubboService{
	
	//拿到数据访问层的接口对象
	@Resource
	private TbContentMapper tbContentMapper;
	
	@Override
	public EasyUIDataGrid selContentByPage(long categoryId, int page, int rows) {
		//先分页
		PageHelper.startPage(page, rows);
		TbContentExample example = new TbContentExample();
		if(categoryId!=0){
			example.createCriteria().andCategoryIdEqualTo(categoryId);
		}
		List<TbContent> list = tbContentMapper.selectByExampleWithBLOBs(example);
		PageInfo<TbContent> pi = new PageInfo<>(list);
		EasyUIDataGrid dataGrid = new EasyUIDataGrid();
		dataGrid.setRows(pi.getList());
		dataGrid.setTotal(pi.getTotal());
		return dataGrid;
	}

	@Override
	public int insTbContent(TbContent tbcontent) {
		return tbContentMapper.insertSelective(tbcontent);
	}

	@Override
	public List<TbContent> selectPic(int count, boolean isSort) {
		TbContentExample example = new TbContentExample();
		if(isSort){
			//此处的updated必须与表中的字段updated保持一致
			example.setOrderByClause("updated desc");
		}
		//以分页的方式显示
		if(count!=0){
			PageHelper.startPage(1, count);
			List<TbContent> list = tbContentMapper.selectByExampleWithBLOBs(example);
			PageInfo<TbContent> pi = new PageInfo<>(list);
			return pi.getList();
		}else{
			return tbContentMapper.selectByExampleWithBLOBs(example);
		}
	}

}
