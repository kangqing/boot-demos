package com.spring.tx.dao;

import com.spring.tx.entity.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * @author kangqing
 * @since 2021/11/18 19:42
 */
@Repository
@RequiredArgsConstructor
public class OrderDao {

    private final JdbcTemplate jdbcTemplate;

    /**
     * 创建订单
     * @param order 订单
     * @return
     */
    public int saveOrder(Order order) {
        String sql = "insert into order_info (id, order_no) values (?, ?)";

        return jdbcTemplate.update(sql, order.getId(), order.getOrderNo());
    }


}
