package com.example.demo.rabbitmq.service;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;


public interface OnMessageArrivedListener {

    /**
     * 消息回调
     *
     * @param message 回调的消息体
     * @param channel 回调的
     * @return
     */
    boolean onMessageArrived(Message message, Channel channel) throws InterruptedException;
}
