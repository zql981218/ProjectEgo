package com.ego.portal.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ego.commons.utils.JsonUtils;
import com.ego.dubbo.service.TbContentDubboService;
import com.ego.pojo.TbContent;
import com.ego.portal.service.TbContentService;
import com.ego.redis.dao.JedisDao;

@Service
public class TbContentServiceImpl implements TbContentService {

	@Reference
	private TbContentDubboService tbContentDubboService;
	//引用kgo-redis中增删改查方法
		@Resource
		private JedisDao jedisDao;
		//拿到属性文件的key值
		@Value("${redis.bigpic.key}")
		private String key;
	
	@Override
	public String showImage() {
		//判断Redis中是否存在数据
				if(jedisDao.exists(key)){
					String value = jedisDao.get(key);
					if(value!=null && !value.equals("")){
						return value;
					}
				}
				//如果Redis数据库中没有图片数据，则从mysql数据库中查询
				List<TbContent> list = tbContentDubboService.selectPic(6, true);
				List<Map<String, Object>> listResult = new ArrayList<>();
				for (TbContent tc : list) {
					Map<String, Object> map = new HashMap<>();
					map.put("srcB", tc.getPic2());
					map.put("height", 240);
					map.put("alt", "");
					map.put("width", 670);
					map.put("src", tc.getPic());
					map.put("href", tc.getUrl());
					map.put("widthB", 550);
					map.put("heightB", 240);
					
					listResult.add(map);
				}
				//需要将集合中数据转换为json格式的数据类型
				String jsonData = JsonUtils.objectToJson(listResult);
				//那数据存放到Redis数据库中
				jedisDao.set(key, jsonData);
				return jsonData;
	}

}
