package com.kangqing.simple;

import lombok.Data;

import java.io.Serializable;

/**
 * @author kangqing
 * @since 2023/7/11 16:01
 */
@Data
public class PublisherConfirmMessage<T> implements Serializable {

    /**
     * 发布确认，同步确认
     */
    public static final String QUEUE = "QUEUE_PUBLISHER_CONFIRM_SIMPLE";

    public static final String EXCHANGE = "EXCHANGE_PUBLISHER_CONFIRM_SIMPLE";

    public static final String ROUTING_KEY = "ROUTING_KEY_PUBLISHER_CONFIRM_SIMPLE";

    /**
     * 编号
     */
    private Integer id;

    private T data;
}
