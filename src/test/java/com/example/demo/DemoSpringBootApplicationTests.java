package com.example.demo;

import com.example.demo.bean.FanoutMessage;
import com.example.demo.consts.ConstantUtil;
import com.example.demo.rabbitmq.config.RabbitConfig;
import com.example.demo.rabbitmq.config.RabbitUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoSpringBootApplication.class)
public  class DemoSpringBootApplicationTests {

    @Test
    public void contextLoads() {
//        FanoutMessage message = new FanoutMessage();
//        message.setType(ConstantUtil.MessageType.startSpike.getType());
//        message.setMessageBody("spike012923936979584768");
//        message.setListenerCount(100);
//        rabbitUtil.sendMessageToExchange(RabbitConfig.FANOUT_EXCHANGE_SPIKE,
//                message);
//        System.out.println("haha");
    }

}
