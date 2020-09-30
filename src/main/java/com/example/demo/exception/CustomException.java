package com.example.demo.exception;


import com.example.demo.consts.ResultPacketCode;

/**
 * 权限Exception
 */
public class CustomException extends RuntimeException {

	/**
	 *
	 */
	private static final long serialVersionUID = -2951242561051566828L;

	private Integer code;

	/**
     *
     */
    public CustomException(){
        super();
    }

    /**
     *
     * @param message
     */
    public CustomException(String message){
    	super(message);
    	this.code = ResultPacketCode.APIResultCode.Fail.getCode();
    }

    /**
     *
     * @param code
     * @param message
     */
    public CustomException(Integer code, String message){
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
