package com.spring.tx;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.spring.tx.service.OrderService;

/**
 * @author kangqing
 * @since 2021/11/18 20:26
 */
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig.class);
        OrderService orderService = context.getBean(OrderService.class);
        orderService.submitOrder();

    }
}
