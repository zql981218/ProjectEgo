package com.ego.manage.controller;

import java.io.IOException;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ego.manage.service.PicService;

@Controller
public class PicController {

	//拿到接口对象
	@Resource
	private PicService picService;
	
	//上传图片
	@RequestMapping("pic/upload")
	@ResponseBody
	public Map<String, Object> uploadPic(MultipartFile uploadFile){
		Map<String, Object> map = null;
		//调用业务逻辑层的方法
		try {
			map = picService.upload(uploadFile);
		} catch (IOException e) {
			e.printStackTrace();
			map.put("error", 1);
			map.put("message", "文件上传失败(控制器层)！");
		}
		return map;
	}
}
