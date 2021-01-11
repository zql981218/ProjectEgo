package com.ego.dubbo.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ego.commons.pojo.EasyUIDataGrid;
import com.ego.dubbo.service.TbItemParamDubboService;
import com.ego.mapper.TbItemParamMapper;
import com.ego.pojo.TbItemParam;
import com.ego.pojo.TbItemParamExample;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
@Service
public class TbItemParamDubboServiceImpl implements TbItemParamDubboService{

	//拿到数据访问层的接口对象
	@Resource
	private TbItemParamMapper tbItemParamMapper;
	
	@Override
	public EasyUIDataGrid showPage(int page, int rows) {
		//1.先用分页插件分页
		PageHelper.startPage(page, rows);
		List<TbItemParam> list = tbItemParamMapper.selectByExampleWithBLOBs(new TbItemParamExample());
		//2.将查询结果存放到集合中
		PageInfo<TbItemParam> pi = new PageInfo<>(list);
		EasyUIDataGrid dataGrid = new EasyUIDataGrid();
		dataGrid.setRows(pi.getList());
		dataGrid.setTotal(pi.getTotal());
		return dataGrid;
	}

	@Override
	public int delById(String ids) throws Exception {
		int index = 0;
		String[] idsStr = ids.split(",");
		for (String id : idsStr) {
			index += tbItemParamMapper.deleteByPrimaryKey(Long.parseLong(id));
		}
		if(index==idsStr.length){
			return 1;
		}else{
			throw new Exception("删除失败！数据还原！");
		}
	}

	@Override
	public TbItemParam selById(long catid) {
		TbItemParamExample example = new TbItemParamExample();
		//确保前台页面传递的参数ID与数据库中ID相匹配
		example.createCriteria().andItemCatIdEqualTo(catid);
		List<TbItemParam> list = tbItemParamMapper.selectByExampleWithBLOBs(example);
		if(list!=null && list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	//增加
	@Override
	public int insItemParam(TbItemParam tbitemparam) {
		//用对象的方式增加数据
		return tbItemParamMapper.insertSelective(tbitemparam);
	}
	
	

}
