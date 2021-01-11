package com.ego.manage.service;

import java.util.List;

import com.ego.commons.pojo.EasyUiTree;
import com.ego.commons.pojo.EgoResult;
import com.ego.pojo.TbContentCategory;

public interface TbContentCategoryServie {
	/**
	 * 查询
	 * @param id
	 * @return
	 */
	public List<EasyUiTree> selByPid(long id);
	/**
	 * 新增
	 * @param cate
	 * @return
	 */
	public EgoResult create(TbContentCategory cate);
	
	/**
	 * 修改
	 * @param cate
	 * @return
	 */
	public EgoResult update(TbContentCategory cate);
	
	/**
	 * 逻辑删除（修改状态码，不会真的删除）
	 * @param cate
	 * @return
	 */
	public EgoResult delete(TbContentCategory cate);
}
