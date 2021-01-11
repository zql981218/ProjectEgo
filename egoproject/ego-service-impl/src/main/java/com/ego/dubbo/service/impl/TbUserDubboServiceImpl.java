package com.ego.dubbo.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ego.dubbo.service.TbUserDubboService;
import com.ego.mapper.TbUserMapper;
import com.ego.pojo.TbUser;
import com.ego.pojo.TbUserExample;

@Service
public class TbUserDubboServiceImpl implements TbUserDubboService {

	//拿到数据访问层的对象
	@Resource
	private TbUserMapper tbUserMapper;
		
	@Override
	public TbUser selectByUser(TbUser tbuser) {
		TbUserExample example = new TbUserExample();
		//要将参数传来的“用户名”和“密码”与数据库中“用户名”和“密码”进行比较
		example.createCriteria().andUsernameEqualTo(tbuser.getUsername()).andPasswordEqualTo(tbuser.getPassword());
		List<TbUser> list = tbUserMapper.selectByExample(example);
		if(list!=null && list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}

}
