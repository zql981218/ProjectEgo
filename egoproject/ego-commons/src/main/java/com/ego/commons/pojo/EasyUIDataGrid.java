package com.ego.commons.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * 将需要显示的数据，封装到该类中，
 * 后期直接返回该类，即可拿到对应的数据
 * @author Administrator
 *必须实现Serializable序列化接口
 */
public class EasyUIDataGrid implements Serializable{
	
	//当前页显示的数据内容
	private List<?> rows;
	//总共显示的条数
	private long  total;
	public List<?> getRows() {
		return rows;
	}
	public void setRows(List<?> rows) {
		this.rows = rows;
	}
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}

}
