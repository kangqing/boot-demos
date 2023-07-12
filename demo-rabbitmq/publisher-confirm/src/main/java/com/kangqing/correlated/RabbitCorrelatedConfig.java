package com.kangqing.correlated;

import com.kangqing.simple.PublisherConfirmMessage;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author kangqing
 * @since 2023/7/11 20:32
 */
@Configuration
public class RabbitCorrelatedConfig {
    // 创建 Queue
    @Bean
    public Queue demo02Queue() {
        return new Queue(CorrelatedConfirmMessage.QUEUE, // Queue 名字
                true, // durable: 是否持久化
                false, // exclusive: 是否排它
                false); // autoDelete: 是否自动删除
    }

    // 创建 Direct Exchange
    @Bean
    public DirectExchange demo02Exchange() {
        return new DirectExchange(CorrelatedConfirmMessage.EXCHANGE,
                true,  // durable: 是否持久化
                false);  // exclusive: 是否排它
    }

    // 创建 Binding
    // Exchange：Demo01Message.EXCHANGE
    // Routing key：Demo01Message.ROUTING_KEY
    // Queue：Demo01Message.QUEUE
    @Bean
    public Binding demo02Binding() {
        return BindingBuilder.bind(demo02Queue()).to(demo02Exchange())
                .with(CorrelatedConfirmMessage.ROUTING_KEY);
    }
}
