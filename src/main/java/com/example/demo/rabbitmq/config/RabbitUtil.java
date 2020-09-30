package com.example.demo.rabbitmq.config;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitUtil {

    private static final Logger logger = LoggerFactory.getLogger(RabbitUtil.class);

    private final RabbitAdmin rabbitAdmin;

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public RabbitUtil(RabbitAdmin rabbitAdmin, RabbitTemplate rabbitTemplate){
        this.rabbitAdmin = rabbitAdmin;
        this.rabbitTemplate = rabbitTemplate;
    }

    /**
     * 转换Message对象
     * @param messageType 返回消息类型 MessageProperties类中常量
     * @param msg
     * @return
     */
    public Message getMessage(String messageType, Object msg){
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setContentType(messageType);
        messageProperties.setContentEncoding("UTF-8");
        logger.debug("发送的消息：" + msg.toString());
        Message message = new Message(JSONObject.toJSONString(msg).getBytes(),messageProperties);
        return message;
    }

    /**
     * 有绑定Key的Exchange发送
     * @param routingKey
     * @param msg
     */
    public void sendMessageToExchange(String topicExchange, String routingKey, Object msg){
        Message message = getMessage(MessageProperties.CONTENT_TYPE_JSON,msg);
        rabbitTemplate.convertAndSend(topicExchange, routingKey, message);
    }

    /**
     * 没有绑定KEY的Exchange发送
     * @param exchange
     * @param msg
     */
    public void sendMessageToExchange(String topicExchange, AbstractExchange exchange, String msg){
        addExchange(exchange);
        logger.info("RabbitMQ send "+exchange.getName()+"->"+msg);
        rabbitTemplate.convertAndSend(topicExchange,msg);
    }

    /**
     * 没有绑定路由key的Exchange发送
     * @param fanoutExchange
     * @param msg
     */
    public void sendMessageToExchange(String fanoutExchange, Object msg){
        Message message = getMessage(MessageProperties.CONTENT_TYPE_JSON,msg);
        rabbitTemplate.convertAndSend(fanoutExchange,"",message);
    }

    /**
     * 给queue发送消息
     * @param queueName
     * @param msg
     */
    public void sendToQueue(String queueName, String msg){
        sendToQueue(DirectExchange.DEFAULT, queueName, msg);
    }

    /**
     * 给direct交换机指定queue发送消息
     * @param directExchange
     * @param queueName
     * @param msg
     */
    public void sendToQueue(DirectExchange directExchange, String queueName, String msg){
        Queue queue = new Queue(queueName);
        addQueue(queue);
        Binding binding = BindingBuilder.bind(queue).to(directExchange).withQueueName();
        rabbitAdmin.declareBinding(binding);
        //设置消息内容的类型，默认是 application/octet-stream 会是 ASCII 码值
        rabbitTemplate.convertAndSend(directExchange.getName(), queueName, msg);
    }

    /**
     * 给queue发送消息
     * @param queueName
     */
    public String receiveFromQueue(String queueName){
        return receiveFromQueue(DirectExchange.DEFAULT, queueName);
    }

    /**
     * 给direct交换机指定queue发送消息
     * @param directExchange
     * @param queueName
     */
    public String receiveFromQueue(DirectExchange directExchange, String queueName){
        Queue queue = new Queue(queueName);
        addQueue(queue);
        Binding binding = BindingBuilder.bind(queue).to(directExchange).withQueueName();
        rabbitAdmin.declareBinding(binding);
        String messages = (String)rabbitTemplate.receiveAndConvert(queueName);
        System.out.println("Receive:"+messages);
        return messages;
    }

    /**
     * 创建Exchange
     * @param exchange
     */
    public void addExchange(AbstractExchange exchange){
        rabbitAdmin.declareExchange(exchange);
    }

    /**
     * 删除一个Exchange
     * @param exchangeName
     */
    public boolean deleteExchange(String exchangeName){
        return rabbitAdmin.deleteExchange(exchangeName);
    }

    /**
     * 创建一个指定的Queue
     * @param queue
     * @return queueName
     */
    public String addQueue(Queue queue){
        return rabbitAdmin.declareQueue(queue);
    }

    /**
     * 删除一个queue
     * @return true if the queue existed and was deleted.
     * @param queueName
     */
    public boolean deleteQueue(String queueName){
        return rabbitAdmin.deleteQueue(queueName);
    }

    /**
     * 绑定一个队列到一个匹配型交换器使用一个routingKey
     * @param queue
     * @param exchange
     * @param routingKey
     */
    public void addBinding(Queue queue , TopicExchange exchange, String routingKey){
        Binding binding = BindingBuilder.bind(queue).to(exchange).with(routingKey);
        rabbitAdmin.declareBinding(binding);
    }


    /**
     * 绑定一个队列到FanoutExchange广播交换机
     * @param queue
     * @param exchange
     */
    public void addBinding(Queue queue , FanoutExchange exchange){
        Binding binding = BindingBuilder.bind(queue).to(exchange);
        rabbitAdmin.declareBinding(binding);
    }


    /**
     * 去掉一个binding
     * @param binding
     */
    public void removeBinding(Binding binding){
        rabbitAdmin.removeBinding(binding);
    }

    public void getQueue() {
        rabbitTemplate.receiveAndConvert();
    }
}