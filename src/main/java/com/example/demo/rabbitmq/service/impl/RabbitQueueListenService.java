package com.example.demo.rabbitmq.service.impl;

import com.alibaba.fastjson.TypeReference;
import com.example.demo.consts.ConstantUtil;
import com.example.demo.rabbitmq.config.RabbitUtil;
import com.example.demo.rabbitmq.service.OnMessageArrivedListener;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 生成临时队列
 */
@Service
public class RabbitQueueListenService {

    private static final Logger logger = LoggerFactory.getLogger(RabbitQueueListenService.class);

    @Autowired
    private ConnectionFactory connectionFactory;

    @Autowired
    RabbitUtil rabbitUtil;

    Map<String, SimpleMessageListenerContainer> containerMap = new ConcurrentHashMap<>();

    /**
     * 申明队列绑定广播交换机
     * @param queueName
     * @param exchange
     */
    public void initQueue(String queueName, FanoutExchange exchange, OnMessageArrivedListener onMessageArrivedListener) {
        /**
         * 声明创建队列，如果不存在就创建，存在就不创建
         * Queue.DeclareOk queueDeclare(String queue, boolean durable, boolean exclusive, boolean autoDelete, Map<String, Object> arguments) throws IOException;
         * 参数说明：
         * String queue：队列名
         * durable：是否持久化，队列声明默认是存放到内存中的，如果rabbitmq重启会丢失，如果重启之后还存在就要使用队列持久化，保存到Erlang自带的Mnesia数据库中,dang rabbitmq重启之后会读取该数据库
         * exclusive：是否排外，作用：
         *              1. 当连接关闭时connection.close()该队列是否会自动删除；
         *              2. 该队列是否是私有的private ，如果不是排外的，可以使用两个消费者都访问同一个队列，没有任何问题，如果是排外的，就会对当前的队列加锁，其他通道channel是无法访问的，强制访问会报错：
         *                  com.rabbitmq.client.ShutdownSignalException: channel error; protocol method: #method<channel.close>(reply-code=405, reply-text=RESOURCE_LOCKED - cannot obtain exclusive access to locked queue 'queue_name' in vhost '/', class-id=50, method-id=20)
         *                  一般为true的话用于一个队列只能有一个消费者来消费的场景
         * autoDelete：是否自动删除，当最后一个消费者断开连接之后对垒是否自动删除，可以通过 RabbitMQ Management 查看某个队列的消费者数量，当consumers= 0时会自动删除
         */
        Queue queue = new Queue(queueName, true, false, true);
        // 添加队列
        rabbitUtil.addQueue(queue);
        // 绑定队列到Exchange
        rabbitUtil.addBinding(queue, exchange);

        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);

        // 监听队列的名称
        container.setQueueNames(queueName);
        container.setExposeListenerChannel(true);
        // 设置每个消费者获取的最大消息数量
        container.setPrefetchCount(ConstantUtil.FANOUT_MAX_NUM);
        // 消费者的个数
        container.setConcurrentConsumers(5);
        // 设置确认模式为手工确认
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        container.setMessageListener((ChannelAwareMessageListener) (message, channel) -> {
            logger.debug("spike.# 消费消息--------------：" + new String(message.getBody()));

            if (onMessageArrivedListener.onMessageArrived(message, channel)) {
                // 确认消息消费成功
                channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            } else {
                // 处理失败，把消息重新放回队列
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
            }
        });
        container.start();
        containerMap.put(queueName, container);
    }

    /**
     * 申明队列绑定交换机,不设置监听
     * @param queueName
     * @param exchange
     */
    public void initQueue(String queueName, String routingKey, TopicExchange exchange) {
        /**
         * 声明创建队列，如果不存在就创建，存在就不创建
         * Queue.DeclareOk queueDeclare(String queue, boolean durable, boolean exclusive, boolean autoDelete, Map<String, Object> arguments) throws IOException;
         * 参数说明：
         * String queue：队列名
         * durable：是否持久化，队列声明默认是存放到内存中的，如果rabbitmq重启会丢失，如果重启之后还存在就要使用队列持久化，保存到Erlang自带的Mnesia数据库中,dang rabbitmq重启之后会读取该数据库
         * exclusive：是否排外，作用：
         *              1. 当连接关闭时connection.close()该队列是否会自动删除；
         *              2. 该队列是否是私有的private ，如果不是排外的，可以使用两个消费者都访问同一个队列，没有任何问题，如果是排外的，就会对当前的队列加锁，其他通道channel是无法访问的，强制访问会报错：
         *                  com.rabbitmq.client.ShutdownSignalException: channel error; protocol method: #method<channel.close>(reply-code=405, reply-text=RESOURCE_LOCKED - cannot obtain exclusive access to locked queue 'queue_name' in vhost '/', class-id=50, method-id=20)
         *                  一般为true的话用于一个队列只能有一个消费者来消费的场景
         * autoDelete：是否自动删除，当最后一个消费者断开连接之后对垒是否自动删除，可以通过 RabbitMQ Management 查看某个队列的消费者数量，当consumers= 0时会自动删除
         */
        Queue queue = new Queue(queueName, true, false, true);
        // 添加队列
        rabbitUtil.addQueue(queue);
        // 绑定队列到Exchange
        rabbitUtil.addBinding(queue, exchange, routingKey);
    }

    /**
     * 创建临时队列用于一些业务（如：秒杀）
     *
     * @param queueName                队列名称 规则（ 秒杀情况下: spike + 秒杀id）
     * @param routingKey               路由名称规则（ 秒杀情况下: spike + 秒杀id + ".#"）
     * @param exchange                 交换机（秒杀场合使用秒杀专用交换机：spikeExchange）
     * @param maxCount                 队列最大长度
     * @param typeReference            该队列使用的传输类型（例如：秒杀使用pojo Spike）
     * @param onMessageArrivedListener 队列监听回调函数
     */
    public void initQueue(String queueName, String routingKey, TopicExchange exchange, int maxCount,
                          TypeReference typeReference, OnMessageArrivedListener onMessageArrivedListener) {

        /**
         * 声明创建队列，如果不存在就创建，存在就不创建
         * Queue.DeclareOk queueDeclare(String queue, boolean durable, boolean exclusive, boolean autoDelete, Map<String, Object> arguments) throws IOException;
         * 参数说明：
         * String queue：队列名
         * durable：是否持久化，队列声明默认是存放到内存中的，如果rabbitmq重启会丢失，如果重启之后还存在就要使用队列持久化，保存到Erlang自带的Mnesia数据库中,dang rabbitmq重启之后会读取该数据库
         * exclusive：是否排外，作用：
         *              1. 当连接关闭时connection.close()该队列是否会自动删除；
         *              2. 该队列是否是私有的private ，如果不是排外的，可以使用两个消费者都访问同一个队列，没有任何问题，如果是排外的，就会对当前的队列加锁，其他通道channel是无法访问的，强制访问会报错：
         *                  com.rabbitmq.client.ShutdownSignalException: channel error; protocol method: #method<channel.close>(reply-code=405, reply-text=RESOURCE_LOCKED - cannot obtain exclusive access to locked queue 'queue_name' in vhost '/', class-id=50, method-id=20)
         *                  一般为true的话用于一个队列只能有一个消费者来消费的场景
         * autoDelete：是否自动删除，当最后一个消费者断开连接之后对垒是否自动删除，可以通过 RabbitMQ Management 查看某个队列的消费者数量，当consumers= 0时会自动删除
         */
        Queue queue = new Queue(queueName, true, false, true);
        // 添加队列
        rabbitUtil.addQueue(queue);

        // 绑定队列到Exchange
        rabbitUtil.addBinding(queue, exchange, routingKey);

        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);

        // 监听队列的名称
        container.setQueueNames(queueName);
        container.setExposeListenerChannel(true);
        // 设置每个消费者获取的最大消息数量
        container.setPrefetchCount(maxCount);
        // 消费者的个数
        container.setConcurrentConsumers(2000);
        // 设置确认模式为手工确认
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        container.setMessageListener(new ChannelAwareMessageListener() {
            @Override
            public void onMessage(Message message, Channel channel) throws Exception {
                logger.debug("spike.# 消费消息--------------：" + new String(message.getBody()));

                if (onMessageArrivedListener.onMessageArrived(message, channel)) {
                    // 确认消息消费成功
                    channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
                } else {
                    // 处理失败，把消息重新放回队列
                    channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
                }
            }

            @Override
            public void onMessage(Message message) {
            }
        });
        container.start();
        containerMap.put(queueName, container);
    }

    /**
     * 监听queue
     * @param queueName
     * @param maxCount
     * @param onMessageArrivedListener
     */
    public boolean bindQueue(String queueName,int maxCount,OnMessageArrivedListener onMessageArrivedListener){
        try {
            SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
            // 监听队列的名称
            container.setQueueNames(queueName);
            container.setExposeListenerChannel(true);
            // 设置每个消费者获取的最大消息数量
            container.setPrefetchCount(maxCount);
            // 消费者的个数
            container.setConcurrentConsumers(10);
            // 设置确认模式为手工确认
            container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
            container.setMessageListener(new ChannelAwareMessageListener() {
                @Override
                public void onMessage(Message message, Channel channel) throws Exception {
                    if (onMessageArrivedListener.onMessageArrived(message, channel)) {
                        // 确认消息消费成功
                        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
                    } else {
                        // 处理失败，把消息重新放回队列
                        channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
                    }
                }
                @Override
                public void onMessage(Message message) {
                }
            });
            container.start();
            containerMap.put(queueName, container);
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    /**
     * 关闭队列中的所有监听
     * @param queueName
     */
    public void removeQueue(String queueName) {
        if(containerMap.containsKey(queueName)) {
            containerMap.get(queueName).stop();
            containerMap.remove(queueName);
        }
    }
}
