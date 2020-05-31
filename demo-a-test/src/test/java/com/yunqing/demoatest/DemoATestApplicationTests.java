package com.yunqing.demoatest;

import cn.hutool.crypto.SecureUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
@Slf4j
class DemoATestApplicationTests {

    /**
     * 测试Md5非盐值加密----------result : 可以轻松被破解
     * 加盐之后就很难破解了，加盐实际上就是给要加密的密文变得更复杂一些
     */
    @Test
    void md5() {
        log.info(SecureUtil.md5("1234cnmasxjklkfdhasuifiads../dskodfjsa@@787ew2456"));

    }

    /**
     * 测试因为直接new一个double类型的BigDecimal会丢失精度的问题
     */
    @Test
    void contextLoads() {
        BigDecimal b1 = new BigDecimal(1.745);
        BigDecimal b2 = new BigDecimal(0.745);
        log.info("输出b1 = {}", b1);
        log.info("输出b2 = {}", b2);
        //使用 ROUND_HALF_UP 四舍五入,保留2位小数
        b1 = b1.setScale(2, BigDecimal.ROUND_HALF_UP);
        b2 = b2.setScale(2, BigDecimal.ROUND_HALF_UP);
        log.info("输出b1 = {}", b1);
        log.info("输出b2 = {}", b2);
        /**
         * 解决办法：
         * 1. 使用BigDecimal(String val)的构造方法创建对象
         * new BigDecimal("1.745");
         * new BigDecimal("0.745");
         * 2. 使用使用BigDecimal的valueOf(double val)方法创建对象
         * BigDecimal.valueOf(1.745);
         * BigDecimal.valueOf(0.745);
         */
    }

}
