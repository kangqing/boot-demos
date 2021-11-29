package com.spring.tx.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * @author kangqing
 * @since 2021/11/18 20:03
 */
@Repository
@RequiredArgsConstructor
public class ProductDao {

    private final JdbcTemplate jdbcTemplate;

    public int updateProductStockCountById(Long id, Integer stockCount) {
        String sql = "update product_info set stock = stock - ? where id = ?";

        return jdbcTemplate.update(sql, stockCount, id);
    }
}
