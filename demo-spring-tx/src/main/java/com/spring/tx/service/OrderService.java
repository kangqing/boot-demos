package com.spring.tx.service;

import com.spring.tx.dao.OrderDao;
import com.spring.tx.dao.ProductDao;
import com.spring.tx.entity.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

/**
 * @author kangqing
 * @since 2021/11/18 20:08
 */
@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderDao orderDao;

    private final ProductService productService;

    /**
     * 生成订单，扣减库存
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void submitOrder() {
        Order order = new Order();
        long number = Math.abs(new Random().nextInt(500));
        order.setId(number);
        order.setOrderNo("order_" + number);
        orderDao.saveOrder(order);
        // 扣减库存
        productService.updateProductStockCountById(1L, 1);
    }
}
