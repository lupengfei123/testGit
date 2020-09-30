package com.example.demo.exception;

/**
* @Description: 自定义参数校验异常
*/
public class CustomParamCheckException extends CustomException {

    public CustomParamCheckException() {
    }

    public CustomParamCheckException(String message) {
        super(message);
    }

    public CustomParamCheckException(Integer code, String message) {
        super(code, message);
    }
}