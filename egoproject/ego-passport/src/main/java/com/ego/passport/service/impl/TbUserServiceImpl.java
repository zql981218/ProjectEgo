package com.ego.passport.service.impl;

import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ego.commons.pojo.EgoResult;
import com.ego.commons.utils.CookieUtils;
import com.ego.commons.utils.JsonUtils;
import com.ego.dubbo.service.TbUserDubboService;
import com.ego.passport.service.TbUserService;
import com.ego.pojo.TbUser;
import com.ego.redis.dao.JedisDao;
@Service
public class TbUserServiceImpl implements TbUserService {
	
	//�õ����ṩ�ߡ����Ľӿڶ���
		@Reference
		private TbUserDubboService tbUserDubboService;
		
		@Resource
		private  JedisDao jedisDao;
		@Override
		public EgoResult login(TbUser tbuser,HttpServletRequest request,HttpServletResponse response) {
			EgoResult er = new EgoResult();
			TbUser userSelect = tbUserDubboService.selectByUser(tbuser);
			if(userSelect!=null){
				er.setStatus(200);
				//�������userSelect�е���Ϣ��������Redis��
				String key = UUID.randomUUID().toString();
				String value = JsonUtils.objectToJson(userSelect);
				//��key��value�����Redis��
				jedisDao.set(key, value);
				//��Redis������������Чʱ��
				jedisDao.expire(key, 7*24*60*60);
				//��Ҫ��Redis�е����ݷ���Cookie��
				CookieUtils.setCookie(request, response, "TT_TOKEN", key, 7*24*60*60);
			}else{
				er.setMsg("�û������������");
			}
			return er;
		}
		@Override
		public EgoResult getUserInfo(String token) {
			EgoResult er = new EgoResult();
			String value = jedisDao.get(token);
			if(value!=null && !value.equals("")){
				TbUser tbuser = JsonUtils.jsonToPojo(value, TbUser.class);
				
				er.setStatus(200);
				er.setMsg("OK");
				er.setData(tbuser);
			}else{
				er.setMsg("��ȡʧ�ܣ�");
			}
			
			return er;
		}
		
		
		@Override
		public EgoResult logout(String token, HttpServletRequest request,
				HttpServletResponse response) {
			//1.ɾ��Redis������
			jedisDao.del(token);
			//2.ɾ��Cookie������
			CookieUtils.deleteCookie(request, response, token);
			//3.����״̬
			EgoResult er = new EgoResult();
			er.setStatus(200);
			er.setMsg("OK");
			return er;
		}
}
