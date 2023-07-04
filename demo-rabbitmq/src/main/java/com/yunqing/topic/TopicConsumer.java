package com.yunqing.topic;

import com.yunqing.direct.Demo01Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author kangqing
 * @since 2023/7/1 13:45
 */
@Slf4j
@Component
@RabbitListener(queues = Demo02Message.QUEUE)
public class TopicConsumer {

    @RabbitHandler
    public void onMessage(Demo02Message<String> message) {
        log.info("[onMessage]线程编号：{}, 消息内容: {}", Thread.currentThread().getName(), message.toString());
    }
}
