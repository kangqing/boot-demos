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

    private final ProductDao productDao;

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
        // this 不生成代理对象，相当于被调用的方法无事务，全部回滚
        this.updateProductStockCountById(1L, 1);
        // 事务传播 REQUIRES_NEW 新的事务，所以新事物中的事务已经提交，库存不回滚，订单回滚
        productService.updateProductStockCountById(1L, 1);
        // 故意制造异常
        int i = 1 / 0;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void updateProductStockCountById(Long id, Integer stockCount) {
        productDao.updateProductStockCountById(id, stockCount);
    }
}
