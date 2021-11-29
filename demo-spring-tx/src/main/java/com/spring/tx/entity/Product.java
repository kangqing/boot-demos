package com.spring.tx.entity;

import lombok.Data;

/**
 * 商品类
 * @author kangqing
 * @since 2021/11/18 19:39
 */
@Data
public class Product {
    /**
     * 主键
     */
    private Long id;
    /**
     * 商品名称
     */
    private String name;
    /**
     * 单价
     */
    private String price;
    /**
     * 库存
     */
    private Integer stock;
}
