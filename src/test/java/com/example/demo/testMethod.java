package com.example.demo;

import com.example.demo.bean.FanoutMessage;
import com.example.demo.consts.ConstantUtil;
import com.example.demo.entity.Student;
import com.example.demo.rabbitmq.config.RabbitConfig;
import com.example.demo.rabbitmq.config.RabbitUtil;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

public class testMethod extends DemoSpringBootApplicationTests {
    @Resource
   private RabbitUtil rabbitUtil;

    @Test
    public void testStartMessage() {
        FanoutMessage message = new FanoutMessage();
        message.setType(ConstantUtil.MessageType.startSpike.getType());
        message.setMessageBody("spike012923936979584768");
        message.setListenerCount(100);
        rabbitUtil.sendMessageToExchange(RabbitConfig.FANOUT_EXCHANGE_SPIKE,
                message);
        System.out.println("haha");
    }
    @Test
    public void testStopMessage() {
        FanoutMessage message = new FanoutMessage();
        message.setType(ConstantUtil.MessageType.stopSpike.getType());
        message.setMessageBody("spike007813940398575872");
        rabbitUtil.sendMessageToExchange(RabbitConfig.FANOUT_EXCHANGE_SPIKE,
                message);
        System.out.println("haha");
    }
    @Test
    public void testRabbitQueue() {
        int i = 0;
        while (i < 100000) {
            System.out.println("消息值：" + i);
            rabbitUtil.sendMessageToExchange("exchange-spike", "spike007813940398575871.1", "消息值：" + i);
            i++;
        }
    }

    @Test
    public void threadSendMessage() throws InterruptedException {
        int i = 0;
        while (i < 1000) {
            Thread t;
            t = new Thread(() -> {
                int j = 0;
                while (j < 1000) {
                    System.out.println("消息值：" + j);
                    rabbitUtil.sendMessageToExchange("exchange-spike", "spike007813940398575871.1", "消息值：" + j);
                    j++;
                }
            });
            t.start();
            i++;
        }
        Thread.sleep(1000000);
    }

    @Test
    public void tesTStream(){

    List <Student> list =new ArrayList<>();
        list.add(new Student(1, "Mahesh", 12));
        list.add(new Student(2, "Suresh", 15));
        list.add(new Student(3, "Nilesh", 10));

        list.stream().sorted(Comparator.comparing(Student::getAge).reversed()).forEach(e->System.out.println("Id:"+ e.getId()+", Name: "+e.getName()+", Age:"+e.getAge()));




    }
}
