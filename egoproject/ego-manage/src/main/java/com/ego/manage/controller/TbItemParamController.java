package com.ego.manage.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ego.commons.pojo.EasyUIDataGrid;
import com.ego.commons.pojo.EgoResult;
import com.ego.manage.service.TbItemParamService;
import com.ego.pojo.TbItemParam;

@Controller
public class TbItemParamController {

	//拿到业务层接口对象
	@Resource
	private TbItemParamService tbItemParamService;
	
	@RequestMapping("item/param/list")
	@ResponseBody
	public EasyUIDataGrid showPage(int page,int rows){
		return tbItemParamService.showPage(page, rows);
	}
	
	@RequestMapping("item/param/delete")
	@ResponseBody
	public EgoResult deleteInfo(String ids){
		EgoResult er = new EgoResult();
		int index = 0;
		try {
			index = tbItemParamService.delete(ids);
			if(index==1){
				er.setStatus(200);
			}
		} catch (Exception e) {
//			e.printStackTrace();
			er.setData(e.getMessage());
		}
		return er;
	}
	
	/**
	 * 通过ID查询
	 */
	@RequestMapping("item/param/query/itemcatid/{catid}")
	@ResponseBody
	public EgoResult showById(@PathVariable long catid){
		//调用业务逻辑层的用ID查询信息的方法
		EgoResult er = tbItemParamService.selectById(catid);
		return er;
	}
	
	/**
	 * 增加
	 * @param param
	 * @param catid
	 * @return
	 */
	@RequestMapping("item/param/save/{catid}")
	@ResponseBody
	public EgoResult save(TbItemParam param, @PathVariable long catid){
		param.setItemCatId(catid);
		return tbItemParamService.insertItemParam(param);
	}
}
