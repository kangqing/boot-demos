package com.yunqing.topic;

import lombok.Data;

import java.io.Serializable;

/**
 * @author kangqing
 * @since 2023/7/1 13:32
 */
@Data
public class Demo02Message<T> implements Serializable {
    public static final String QUEUE = "QUEUE_DEMO_02";

    public static final String EXCHANGE = "EXCHANGE_DEMO_02";

    public static final String ROUTING_KEY = "*.kang.qing";

    /**
     * 编号
     */
    private Integer id;

    private T data;
}
