package com.example.demo.pojo;

import java.util.List;


/** 
 * 类说明
 */
public class PageParam<T> {

	private T param;
	
	private Integer pageIndex;
	
	private List<Object> ids;

	public T getParam() {
		return param;
	}

	public void setParam(T param) {
		this.param = param;
	}

	public Integer getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}

	public List<Object> getIds() {
		return ids;
	}

	public void setIds(List<Object> ids) {
		this.ids = ids;
	}
}
