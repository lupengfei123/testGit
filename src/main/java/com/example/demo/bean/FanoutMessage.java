package com.example.demo.bean;

import java.io.Serializable;

/**
 * 广播exchange消息格式
 * @Author:zhuys
 */
public class FanoutMessage implements Serializable {

    private String messageBody;

    private Integer listenerCount;

    private String type;

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }

    public Integer getListenerCount() {
        return listenerCount;
    }

    public void setListenerCount(Integer listenerCount) {
        this.listenerCount = listenerCount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
