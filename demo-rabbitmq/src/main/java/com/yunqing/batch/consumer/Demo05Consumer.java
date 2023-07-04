package com.yunqing.batch.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author kangqing
 * @since 2023/7/2 20:03
 */
@Slf4j
@Component
@RabbitListener(queues = Demo05Message.QUEUE, containerFactory = "consumerBatchContainerFactory")
public class Demo05Consumer {
    @RabbitHandler
    public void onMessage(List<Demo05Message<String>> messages) {
        log.info("[onMessage][线程编号:{} 消息数量：{}]", Thread.currentThread().getId(), messages.size());
    }
}
