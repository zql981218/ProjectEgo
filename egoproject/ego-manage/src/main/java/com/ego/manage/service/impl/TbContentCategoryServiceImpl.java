package com.ego.manage.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ego.commons.pojo.EasyUiTree;
import com.ego.commons.pojo.EgoResult;
import com.ego.commons.utils.IDUtils;
import com.ego.dubbo.service.TbContentCategoryDubboService;
import com.ego.manage.service.TbContentCategoryServie;
import com.ego.pojo.TbContentCategory;

@Service
public class TbContentCategoryServiceImpl implements TbContentCategoryServie{
	
	@Reference
	private TbContentCategoryDubboService tbContentCategoryDubboService;
	@Override
	public List<EasyUiTree> selByPid(long id) {
		List<EasyUiTree> listTree = new ArrayList<>();
		List<TbContentCategory> list = tbContentCategoryDubboService.selectByPid(id);
		for (TbContentCategory cate : list) {
			EasyUiTree tree = new EasyUiTree();
			tree.setId(cate.getId());
			tree.setText(cate.getName());
			tree.setState(cate.getIsParent()?"closed":"open");
			
			listTree.add(tree);
		}
		return listTree;
	}
	@Override
	public EgoResult create(TbContentCategory cate) {
		EgoResult er = new EgoResult();
		//判断查询是否与父节点的名称相等
		List<TbContentCategory> children = tbContentCategoryDubboService.selectByPid(cate.getParentId());
		for (TbContentCategory child : children) {
			if(child.getName().equals(cate.getName())){
				er.setData("该分类已存在！");
				return er;
			}
		}
		//增加
		Date date = new Date();
		cate.setCreated(date);
		cate.setUpdated(date);
		cate.setStatus(1);
		cate.setIsParent(false);
		cate.setSortOrder(1);
//		cate.setName(cate.getName());
		long id = IDUtils.genItemId();
		cate.setId(id);
		int index = tbContentCategoryDubboService.insTbContentCategory(cate);
		//增加后修该节点的状态
		if(index>0){
			TbContentCategory parent = new TbContentCategory();
			parent.setId(cate.getParentId());
			parent.setIsParent(true);
			tbContentCategoryDubboService.updTbContentCategory(parent);
		}
		Map<String, Long> map = new HashMap<>();
		map.put("id", id);
		er.setStatus(200);
		er.setData(map);
		return er;
	}
	@Override
	public EgoResult update(TbContentCategory cate) {
		EgoResult er = new EgoResult();
		TbContentCategory cateSelect = tbContentCategoryDubboService.selById(cate.getId());
		List<TbContentCategory> children = tbContentCategoryDubboService.selectByPid(cateSelect.getParentId());
		for (TbContentCategory child : children) {
			if(child.getName().equals(cate.getName())){
				er.setData("该分类名已存在！");
				return er;
			}
		}
		int index = tbContentCategoryDubboService.updTbContentCategory(cate);
		if(index>0){
			er.setStatus(200);
		}
		return er;
	}
	
	/**
	 * 逻辑删除：修改状态 不是真的删除
	 */
	@Override
	public EgoResult delete(TbContentCategory cate) {
		EgoResult er = new EgoResult();
		//此处的删除即为修改状态
		cate.setStatus(0);
		int index = tbContentCategoryDubboService.updTbContentCategory(cate);
		if(index>0){
			TbContentCategory curr = tbContentCategoryDubboService.selById(cate.getId());
			List<TbContentCategory> list = tbContentCategoryDubboService.selectByPid(curr.getParentId());
			//判断是否存在父节点
			if(list==null||list.size()==0){
				TbContentCategory cate02 = new TbContentCategory();
				cate02.setId(curr.getParentId());
				cate02.setIsParent(false);
				int result = tbContentCategoryDubboService.updTbContentCategory(cate02);
				if(result>0){
					er.setStatus(200);
				}
			}else{
				er.setStatus(200);
			}
		}
		return er;
	}
	
	
}
