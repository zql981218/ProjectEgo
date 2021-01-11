package com.ego.redis.dao;

public interface JedisDao {

	//增加(判断是否存在)
	Boolean exists(String key);
	//删除
	Long del(String key); 
	//修改
	String set(String key,String value);
	//查询
	String get(String key); 
	
	Long expire(String key,int seconds);
}
