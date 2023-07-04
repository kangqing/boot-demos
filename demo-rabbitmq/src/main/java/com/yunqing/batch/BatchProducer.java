package com.yunqing.batch;

import org.springframework.amqp.rabbit.core.BatchingRabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author kangqing
 * @since 2023/7/1 19:33
 */
@Component
public class BatchProducer {

    @Resource
    private BatchingRabbitTemplate batchingRabbitTemplate;

    public void syncSend(Integer id) {
        // 创建 Demo05Message 消息
        DemoBatchMessage<String> message = new DemoBatchMessage<>();
        message.setId(id);
        message.setData("批量消息");
        // 同步发送消息
        batchingRabbitTemplate.convertAndSend(DemoBatchMessage.EXCHANGE, DemoBatchMessage.ROUTING_KEY, message);
    }
}
