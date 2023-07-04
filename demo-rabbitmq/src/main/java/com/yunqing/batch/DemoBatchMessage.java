package com.yunqing.batch;

import lombok.Data;

import java.io.Serializable;

/**
 * RocketMQ 是提供了一个可以批量发送多条消息的 API 。而 Spring-AMQP 提供的批量发送消息，它提供了一个 MessageBatch 消息收集器，
 * 将发送给相同 Exchange + RoutingKey 的消息们，“偷偷”收集在一起，当满足条件时候，一次性批量发送提交给 RabbitMQ Broker 。
 *
 * Spring-AMQP 通过 BatchingRabbitTemplate 提供批量发送消息的功能。如下是三个条件，满足任一即会批量发送：
 *
 * 【数量】batchSize ：超过收集的消息数量的最大条数。
 * 【空间】bufferLimit ：超过收集的消息占用的最大内存。
 * 【时间】timeout ：超过收集的时间的最大等待时长，单位：毫秒。😈 不过要注意，这里的超时开始计时的时间，是以最后一次发送时间为起点。也就说，
 * 每调用一次发送消息，都以当前时刻开始计时，重新到达 timeout 毫秒才算超时。
 * 另外，BatchingRabbitTemplate 提供的批量发送消息的能力比较弱。对于同一个 BatchingRabbitTemplate 对象来说，同一时刻只能有一个批次
 * (保证 Exchange + RoutingKey 相同)，否则会报错。
 * @author kangqing
 * @since 2023/7/1 19:26
 */
@Data
public class DemoBatchMessage<T> implements Serializable {

    public static final String QUEUE = "QUEUE_DEMO_04";

    public static final String EXCHANGE = "EXCHANGE_DEMO_04";

    public static final String ROUTING_KEY = "ROUTING_KEY_04";

    /**
     * 编号
     */
    private Integer id;

    private T data;
}
