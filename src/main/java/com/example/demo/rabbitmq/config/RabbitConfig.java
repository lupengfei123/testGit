package com.example.demo.rabbitmq.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;



@Configuration
public class RabbitConfig {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${spring.rabbitmq.host}")
    private String host;

    @Value("${spring.rabbitmq.port}")
    private int port;

    @Value("${spring.rabbitmq.username}")
    private String username;

    @Value("${spring.rabbitmq.password}")
    private String password;

    // 批处理用
    public static final String EXCHANGE_TASK = "exchange-task";
    public static final String QUEUE_TASK = "queue_task";
    public static final String ROUTINGKEY_TASK = "task.#";

    // 秒杀用
    public static final String EXCHANGE_SPIKE = "exchange-spike";
//    public static final String QUEUE_SPIKE = "queue_spike";
//    public static final String ROUTINGKEY_SPIKE = "spike.#";

    // 秒杀队列广播用
    public static final String FANOUT_EXCHANGE_SPIKE = "fanout_exchange-spike";

    // 退款用
    public static final String EXCHANGE_REFUND = "exchange-refund";
    public static final String QUEUE_REFUND = "c_queue_refund";
    public static final String ROUTINGKEY_REFUND = "refund.#";

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(host, port);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        connectionFactory.setVirtualHost("/");
        // 允许事件的回调
        connectionFactory.setPublisherConfirms(true);
        return connectionFactory;
    }

    @Bean
    public RabbitListenerContainerFactory<?> rabbitListenerContainerFactory(ConnectionFactory connectionFactory){
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        // 序列化对象，允许消息队列传递的内容是对象
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        // 开启手动应答,必须确认消息收到
        factory.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        return factory;
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    //必须是prototype类型
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate template = new RabbitTemplate(connectionFactory());
        template.setMessageConverter(new Jackson2JsonMessageConverter());
        return template;
    }

    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory){
        return new RabbitAdmin(connectionFactory);
    }

    @Bean
    public TopicExchange spikeExchange() {
        return new TopicExchange(EXCHANGE_SPIKE);
    }

    /**
     *
     *  秒杀广播交换机
     *  @return
     */
    @Bean
    public FanoutExchange spikeFanoutExchange() {
        return new FanoutExchange(FANOUT_EXCHANGE_SPIKE);
    }

    @Bean
    public TopicExchange taskExchange() {
        return new TopicExchange(EXCHANGE_TASK);
    }

    @Bean
    public Queue taskQueue() {
        return new Queue(QUEUE_TASK, true); //队列持久
    }

    @Bean
    public Binding taskBinding() {
        return BindingBuilder.bind(taskQueue()).to(taskExchange()).with(RabbitConfig.ROUTINGKEY_TASK);
    }

    @Bean
    public TopicExchange refundExchange() {
        return new TopicExchange(EXCHANGE_REFUND);
    }

    @Bean
    public Queue refundQueue() {
        return new Queue(QUEUE_REFUND, true); //队列持久
    }

    @Bean
    public Binding refundBinding() {
        return BindingBuilder.bind(refundQueue()).to(refundExchange()).with(RabbitConfig.ROUTINGKEY_REFUND);
    }

}