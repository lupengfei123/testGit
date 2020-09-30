package com.example.demo.pojo;


import com.example.demo.consts.ResultPacketCode;

/**
 * api返回对象封装
 */
public class APIResult<T> extends BaseResult {
	
	private T result;

	private Pager pager;

	private Security security;

	public APIResult() {
	    // 返回code默认为Fail，True需手动设值
		super.code = ResultPacketCode.APIResultCode.Fail.getCode();
	}
	
	public APIResult(Integer code, String message) {
		super.code = code;
		super.message = message;
	}

	public T getResult() {
		return result;
	}

	public void setResult(T result) {
		this.result = result;
	}

	public Pager getPager() {
		return pager;
	}

	public void setPager(Pager pager) {
		this.pager = pager;
	}

	public Security getSecurity() {
		return security;
	}

	public void setSecurity(Security security) {
		this.security = security;
	}
}
