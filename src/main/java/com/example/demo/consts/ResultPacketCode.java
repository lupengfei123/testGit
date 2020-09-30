package com.example.demo.consts;

public class ResultPacketCode {
    public enum APIResultCode {
        //失败
        Fail(100),
        //验证失败
        AuthFail(150),
        //成功
        Success(200),
        //系统Error
        SystemError(300),
        //连续发送相同请求
        FrequentFail(400);

        // 定义私有变量
        private Integer code;

        // 构造函数，枚举类型只能为私有
        private APIResultCode(Integer code) {
            this.code = code;
        }

        public Integer getCode() {
            return this.code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }
    }
}
