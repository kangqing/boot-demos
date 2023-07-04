package com.yunqing.fanout;

import lombok.Data;

import java.io.Serializable;

/**
 * 广播消息
 * @author kangqing
 * @since 2023/7/1 13:59
 */
@Data
public class Demo03Message<T> implements Serializable {

    public static final String QUEUE_A = "QUEUE_DEMO_03_A";
    public static final String QUEUE_B = "QUEUE_DEMO_03_B";

    public static final String EXCHANGE = "EXCHANGE_DEMO_03";

    /**
     * 编号
     */
    private Integer id;
    private T data;
}
