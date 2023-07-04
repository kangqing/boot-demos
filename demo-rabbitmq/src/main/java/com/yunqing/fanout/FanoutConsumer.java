package com.yunqing.fanout;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author kangqing
 * @since 2023/7/1 14:04
 */
@Slf4j
@Component
@RabbitListener(queues = Demo03Message.QUEUE_A)
public class FanoutConsumer {

    @RabbitHandler
    public void onMessage(Demo03Message<String> message) {
        log.info("[onMessage][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), message);
    }

    /**
     * 消费两个队列
     */
    @Slf4j
    @Component
    @RabbitListener(queues = Demo03Message.QUEUE_B)
    public static class FanoutConsumerQueue_B {
        @RabbitHandler
        public void onMessage(Demo03Message<String> message) {
            log.info("[onMessage][线程编号:{} 消息内容：{}]", Thread.currentThread().getId(), message);
        }
    }
}
