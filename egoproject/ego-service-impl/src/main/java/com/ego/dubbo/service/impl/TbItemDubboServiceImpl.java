package com.ego.dubbo.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ego.commons.pojo.EasyUIDataGrid;
import com.ego.dubbo.service.TbItemDubboService;
import com.ego.mapper.TbItemDescMapper;
import com.ego.mapper.TbItemMapper;
import com.ego.mapper.TbItemParamItemMapper;
import com.ego.pojo.TbItem;
import com.ego.pojo.TbItemDesc;
import com.ego.pojo.TbItemExample;
import com.ego.pojo.TbItemParamItem;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class TbItemDubboServiceImpl implements TbItemDubboService{

	//拿到数据访问层对象(dao/mapper)
	@Resource
	private TbItemMapper tbItemMapper;
	//拿到“商品描述信息”的dao接口对象
	@Resource
	private TbItemDescMapper tbItemDescMapper;
	
	@Resource
	private TbItemParamItemMapper tbItemParamItemMapper;
	@Override
	public EasyUIDataGrid show(int page, int rows) {
		//分页插件
		PageHelper.startPage(page, rows);
		List<TbItem> list = tbItemMapper.selectByExample(new TbItemExample());
		//com.github.pagehelper提供的工具类，专门用于分页的工具类
		PageInfo<TbItem> pi = new PageInfo<>(list);
		
		EasyUIDataGrid dataGrid = new EasyUIDataGrid();
		dataGrid.setRows(pi.getList());
		dataGrid.setTotal(pi.getTotal());
		return dataGrid;
	}
	
	//
	@Override
	public int updItemStatus(TbItem tbItem) {
		//修改加查询，并且返回操作后结果
		return tbItemMapper.updateByPrimaryKeySelective(tbItem);
	}

	@Override
	public int insTbItemInfo(TbItem tbItem, TbItemDesc tbitemdesc,TbItemParamItem paramItem)
			throws Exception {
		int index = 0;
		//增加商品信息的方法
		 index = tbItemMapper.insertSelective(tbItem);
		//增加商品描述信息的方法
		 index += tbItemDescMapper.insertSelective(tbitemdesc);
		 //增加“商品规格和商品的关系表/类”的信息
		 index += tbItemParamItemMapper.insertSelective(paramItem);
		if(index==3){
			return 1;
		}else{
			throw new Exception("新增失败，数据还原！");
		}
	}

}
