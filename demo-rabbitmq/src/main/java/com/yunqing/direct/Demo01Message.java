package com.yunqing.direct;

import lombok.Data;

import java.io.Serializable;

/**
 * @author kangqing
 * @since 2023/6/30 14:08
 */
@Data
public class Demo01Message<T> implements Serializable {

    public static final String QUEUE = "QUEUE_DEMO_01";

    public static final String EXCHANGE = "EXCHANGE_DEMO_01";

    public static final String ROUTING_KEY = "ROUTING_KEY_01";

    /**
     * 编号
     */
    private Integer id;

    private T data;

}
