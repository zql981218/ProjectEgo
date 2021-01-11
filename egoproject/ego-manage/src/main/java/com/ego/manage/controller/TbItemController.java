package com.ego.manage.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ego.commons.pojo.EasyUIDataGrid;
import com.ego.commons.pojo.EgoResult;
import com.ego.manage.service.TbItemService;
import com.ego.pojo.TbItem;

@Controller
public class TbItemController {

	//拿到业务逻辑对象
	@Resource
	private TbItemService tbItemService;
	// 增加
		@RequestMapping("item/save")
		@ResponseBody
		public EgoResult insertInfo(TbItem tbItem,String desc,String itemParams){
			EgoResult er = new EgoResult();
			int index = 0;
			try {
				index = tbItemService.saveInfo(tbItem, desc,itemParams);
				if(index==1){
					er.setStatus(200);
				}
			} catch (Exception e) {
//				e.printStackTrace();
				er.setData(e.getMessage());
			}
			return er;
		}
	//删除
	@RequestMapping("rest/item/delete")
	@ResponseBody
	public EgoResult deleteInfo(String ids){
		EgoResult eR = new EgoResult();
		//需要调用业务逻辑才删除方法
		int index =tbItemService.updateTbItem(ids, (byte)3);
		if(index == 1){
			eR.setStatus(200);
		}
		return eR;
	}
	//商品上架
	@RequestMapping("rest/item/reshelf")
	@ResponseBody
	public EgoResult reshelf(String ids){
		EgoResult eR = new EgoResult();
		//需要调用业务逻辑才删除方法
		int index =tbItemService.updateTbItem(ids, (byte)1);
		if(index == 1){
			eR.setStatus(200);
		}
		return eR;
	}
	//商品下架
	@RequestMapping("rest/item/instock")
	@ResponseBody
	public EgoResult instock(String ids){
		EgoResult eR = new EgoResult();
		//需要调用业务逻辑才删除方法
		int index =tbItemService.updateTbItem(ids, (byte)2);
		if(index == 1){
			eR.setStatus(200);
		}
		return eR;
	}
	//查询
	@RequestMapping("item/list")
	@ResponseBody
	public EasyUIDataGrid showInfo(int page,int rows){
		
		return tbItemService.show(page, rows);
	}
}
