package com.yunqing.fanout;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author kangqing
 * @since 2023/7/1 14:02
 */
@Component
public class FanoutProducer {

    @Resource
    private RabbitTemplate rabbitTemplate;

    public void syncSend(Integer id, String msg) {
        // 创建 Demo03Message 消息
        Demo03Message<String> message = new Demo03Message<>();
        message.setId(id);
        message.setData(msg);
        // 同步发送消息
        rabbitTemplate.convertAndSend(Demo03Message.EXCHANGE, null, message);
    }
}
