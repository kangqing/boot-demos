package com.yunqing.direct;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author kangqing
 * @since 2023/6/30 15:00
 */
@Slf4j
@Component
@RabbitListener(queues = Demo01Message.QUEUE)
public class Demo01Consumer {

    @RabbitHandler
    public void onMessage(Demo01Message<String> message) {
        log.info("[onMessage]线程编号：{}, 消息内容: {}", Thread.currentThread().getName(), message.toString());
    }
}
