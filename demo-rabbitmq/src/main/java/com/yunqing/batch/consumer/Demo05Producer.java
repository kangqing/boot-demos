package com.yunqing.batch.consumer;

import com.yunqing.direct.Demo01Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author kangqing
 * @since 2023/7/2 20:05
 */
@Component
public class Demo05Producer {

    @Resource
    private RabbitTemplate rabbitTemplate;

    /**
     * 同步发送
     * 指定 Exchange + RoutingKey ，从而路由到一个 Queue 中。
     */
    public void syncSend(Integer id, String str) {
        // 创建 Demo01Message 消息
        final Demo05Message<String> message = new Demo05Message<>();
        message.setId(id);
        message.setData(str);
        // 同步发送消息
        rabbitTemplate.convertAndSend(Demo05Message.EXCHANGE, Demo05Message.ROUTING_KEY, message);
    }
}
