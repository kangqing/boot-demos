package com.yunqing.topic;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author kangqing
 * @since 2023/7/1 13:40
 */
@Component
public class TopicProducer {

    @Resource
    private RabbitTemplate rabbitTemplate;

    public void syncSend(Integer id, String msg, String routingKey) {
        // 创建 Demo02Message 消息
        Demo02Message<String> message = new Demo02Message<>();
        message.setId(id);
        message.setData(msg);
        // 同步发送消息
        rabbitTemplate.convertAndSend(Demo02Message.EXCHANGE, routingKey, message);
    }
}
