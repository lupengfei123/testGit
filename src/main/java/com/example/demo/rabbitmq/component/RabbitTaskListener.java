package com.example.demo.rabbitmq.component;

import com.example.demo.rabbitmq.config.RabbitConfig;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;

import java.io.IOException;


//@Component
public class RabbitTaskListener {


    @Autowired
    ConnectionFactory connectionFactory;

    /**
     * channel.basicAck(message.getMessageProperties().getDeliveryTag(), false); // 消息的标识，false只确认当前一个消息收到，true确认所有consumer获得的消息
     * channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true); // ack返回false，并重新回到队列，api里面解释得很清楚
     * channel.basicReject(message.getMessageProperties().getDeliveryTag(), true); // 拒绝消息
     *
     * @param message     整个消息内容（如果传递的是对象，则可以定义对象，如果传递的是String，则可以定义成String）
     * @param deliveryTag
     * @param channel
     */
    @RabbitListener(queues = RabbitConfig.QUEUE_TASK)
    @RabbitHandler
    public void process(@Payload String message, @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag, Channel channel) {
        System.out.println("spike.# 消费消息：" + message);

        try {
            channel.basicAck(deliveryTag, false);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
