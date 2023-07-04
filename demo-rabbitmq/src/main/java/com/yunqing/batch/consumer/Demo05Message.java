package com.yunqing.batch.consumer;

import lombok.Data;

import java.io.Serializable;

/**
 * 我们已经学习了一种批量消费消息的方式。因为其依赖「4. 批量发送消息」的功能，有点过于苛刻。所以，Spring-AMQP 提供了第二种批量消费消息的方式。
 *
 * 其实现方式是，阻塞等待最多 receiveTimeout 秒，拉取 batchSize 条消息，进行批量消费。
 *
 * 如果在 receiveTimeout 秒内已经成功拉取到 batchSize 条消息，则直接进行批量消费消息。
 * 如果在 receiveTimeout 秒还没拉取到 batchSize 条消息，不再等待，而是进行批量消费消息。
 * 不过 Spring-AMQP 的阻塞等待时长 receiveTimeout 的设计有点“神奇”。
 *
 * 它代表的是，每次拉取一条消息，最多阻塞等待 receiveTimeout 时长。如果等待不到下一条消息，则进入已获取到的消息的批量消费。😈 也就是说，
 * 极端情况下，可能等待 receiveTimeout * batchSize 时长，才会进行批量消费。
 * @author kangqing
 * @since 2023/7/2 19:58
 */
@Data
public class Demo05Message<T> implements Serializable {

    public static final String QUEUE = "QUEUE_DEMO_05";

    public static final String EXCHANGE = "EXCHANGE_DEMO_05";

    public static final String ROUTING_KEY = "ROUTING_KEY_05";

    private Integer id;

    private T data;
}
