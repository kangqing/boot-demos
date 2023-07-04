package com.yunqing.batch;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author kangqing
 * @since 2023/7/1 19:35
 */
@Slf4j
@Component
@RabbitListener(queues = DemoBatchMessage.QUEUE)
public class BatchConsumer {

    @RabbitHandler
    public void onMessage(DemoBatchMessage<String> message) {
        log.info("[onMessage][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), message);
    }
}
