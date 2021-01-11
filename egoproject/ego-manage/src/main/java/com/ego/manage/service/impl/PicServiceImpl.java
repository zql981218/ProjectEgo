package com.ego.manage.service.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ego.commons.utils.FtpUtil;
import com.ego.manage.service.PicService;

@Service
public class PicServiceImpl implements PicService {
	
	//把配置文件中的属性注入到变量中，
	@Value("${ftpclient.host}")
	private String host;
	@Value("${ftpclient.port}")
	private int port;
	@Value("${ftpclient.username}")
	private String username;
	@Value("${ftpclient.password}")
	private String password;
	@Value("${ftpclient.basepath}")
	private String basePath;
	@Value("${ftpclient.filepath}")
	private String filePath;
	
	@Override
	public Map<String, Object> upload(MultipartFile uploadFile)
			throws IOException {
		String filename = uploadFile.getOriginalFilename();
		//用工具类完成文件的上传
		boolean result = FtpUtil.uploadFile(host, port, username, password, basePath, filePath, filename, uploadFile.getInputStream());
		Map<String, Object> map = new HashMap<>();
		if(result){
			//成功时
			map.put("error", 0);
			map.put("url", "http://"+host+"/"+filename);
		}else{
			//失败时
			map.put("error", 1);
			map.put("message", "文件上传失败！");
		}
		return map;
	}

}
