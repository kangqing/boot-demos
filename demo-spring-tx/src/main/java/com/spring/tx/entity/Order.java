package com.spring.tx.entity;

import lombok.Data;

/**
 * 订单类
 * @author kangqing
 * @since 2021/11/18 19:38
 */
@Data
public class Order {
    /**
     * 主键
     */
    private Long id;
    /**
     * 订单编号
     */
    private String orderNo;
}
