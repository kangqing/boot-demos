package com.kangqing.correlated;

import lombok.Data;

import java.io.Serializable;

/**
 * 异步确认
 * @author kangqing
 * @since 2023/7/11 20:30
 */
@Data
public class CorrelatedConfirmMessage<T> implements Serializable {
    /**
     * 发布确认，异步确认
     */
    public static final String QUEUE = "QUEUE_PUBLISHER_CONFIRM_CORRELATED";

    public static final String EXCHANGE = "EXCHANGE_PUBLISHER_CONFIRM_CORRELATED";

    public static final String ROUTING_KEY = "ROUTING_KEY_PUBLISHER_CONFIRM_CORRELATED";

    /**
     * 编号
     */
    private Integer id;

    private T data;
}
