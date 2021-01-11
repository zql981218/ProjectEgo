package com.ego.commons.pojo;
/**
 * 定义一个返回结果类 ，专门用于存放返回的状态
 * @author 起来
 *
 */
public class EgoResult {
	
	private int status;
	
	private Object data;
	
	private String msg;
	
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
