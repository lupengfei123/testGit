package com.example.demo.exception;


import com.example.demo.consts.ResultPacketCode;

/**
 * API权限Exception
 */
public class APIUnexpectedException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6798507089231400881L;
	
	private Integer code;
	
	/** 
     *  
     */  
    public APIUnexpectedException(){  
        super();  
    }  
    
    /** 
     *  
     * @param message 
     */  
    public APIUnexpectedException(String message){  
    	super(message);
    	this.code = ResultPacketCode.APIResultCode.Fail.getCode();
    }  
    
    /**
     * 
     * @param code
     * @param message
     */
    public APIUnexpectedException(Integer code, String message){
    	super(message);
    	this.code = code;
    } 
    
    public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}
}
