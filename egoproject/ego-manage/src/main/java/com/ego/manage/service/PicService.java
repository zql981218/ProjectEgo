package com.ego.manage.service;

import java.io.IOException;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

public interface PicService {
	
	/**
	 * 上传文件的接口
	 * @param uploadFile
	 * @return
	 * @throws IOException 
	 */
	Map<String, Object> upload(MultipartFile uploadFile) throws IOException;
}
