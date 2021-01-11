package com.ego.manage.service.impl;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ego.commons.pojo.EasyUIDataGrid;
import com.ego.dubbo.service.TbContentDubboService;
import com.ego.manage.service.TbContentService;
import com.ego.pojo.TbContent;

@Service
public class TbContentServiceImpl implements TbContentService{

	@Reference
	private TbContentDubboService tbContentDubboService;
	
	@Override
	public EasyUIDataGrid selectContent(long categoryId, int page, int rows) {
		return tbContentDubboService.selContentByPage(categoryId, page, rows);
	}

	@Override
	public int save(TbContent tbcontent) {
		Date date = new Date();
		tbcontent.setCreated(date);
		tbcontent.setUpdated(date);
		return tbContentDubboService.insTbContent(tbcontent);
	}


}
