package com.kangqing.correlated;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

/**
 * @author kangqing
 * @since 2023/7/11 20:36
 */
@Slf4j
@Component
public class RabbitProducerConfirmCallback implements RabbitTemplate.ConfirmCallback{
    public RabbitProducerConfirmCallback(RabbitTemplate rabbitTemplate) {
        rabbitTemplate.setConfirmCallback(this);
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if (ack) {
            log.info("[confirm][Confirm 成功 correlationData: {}]", correlationData);
        } else {
            log.error("[confirm][Confirm 失败 correlationData: {} cause: {}]", correlationData, cause);
        }
    }

}
