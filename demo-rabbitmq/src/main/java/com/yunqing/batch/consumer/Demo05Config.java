package com.yunqing.batch.consumer;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author kangqing
 * @since 2023/7/2 20:00
 */
@Configuration
public class Demo05Config {
    // 创建 Queue
    @Bean
    public Queue demo05Queue() {
        return new Queue(Demo05Message.QUEUE, // Queue 名字
                true, // durable: 是否持久化
                false, // exclusive: 是否排它
                false); // autoDelete: 是否自动删除
    }

    // 创建 Direct Exchange
    @Bean
    public DirectExchange demo05Exchange() {
        return new DirectExchange(Demo05Message.EXCHANGE,
                true,  // durable: 是否持久化
                false);  // exclusive: 是否排它
    }

    // 创建 Binding
    // Exchange：Demo06Message.EXCHANGE
    // Routing key：Demo06Message.ROUTING_KEY
    // Queue：Demo06Message.QUEUE
    @Bean
    public Binding demo05Binding() {
        return BindingBuilder.bind(demo05Queue()).to(demo05Exchange()).with(Demo05Message.ROUTING_KEY);
    }


    @Bean(name = "consumerBatchContainerFactory")
    public SimpleRabbitListenerContainerFactory consumerBatchContainerFactory(
            SimpleRabbitListenerContainerFactoryConfigurer configurer, ConnectionFactory connectionFactory) {
        // 创建 SimpleRabbitListenerContainerFactory 对象
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        configurer.configure(factory, connectionFactory);
        // 额外添加批量消费的属性
        factory.setBatchListener(true);
        factory.setConsumerBatchEnabled(true);
        // <X>
        factory.setBatchSize(10);
        factory.setReceiveTimeout(30 * 1000L);
        return factory;
    }
}
