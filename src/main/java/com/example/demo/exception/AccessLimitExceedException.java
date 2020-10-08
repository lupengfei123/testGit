package com.example.demo.exception;


import com.example.demo.consts.ResultPacketCode;

/**
* @Description: 接口限流异常
*/
public class  AccessLimitExceedException extends RuntimeException {

    private Integer  code;

    public AccessLimitExceedException(){
        super();
    }

    public AccessLimitExceedException(String message){
        super(message);
        this.code = ResultPacketCode.APIResultCode.Fail.getCode();
    }

    public AccessLimitExceedException(Integer code, String message){
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
