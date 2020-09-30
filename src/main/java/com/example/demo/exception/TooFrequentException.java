package com.example.demo.exception;


import com.example.demo.consts.ResultPacketCode;

/**
* @Description: 请求过于频繁异常
*/
public class TooFrequentException extends CustomException {

    public TooFrequentException(){
        super();
    }

    public TooFrequentException(String message){
        super(ResultPacketCode.APIResultCode.FrequentFail.getCode(),message);
    }
}
