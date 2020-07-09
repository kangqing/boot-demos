package com.yunqing.demoatest;

import cn.hutool.crypto.SecureUtil;
import com.yunqing.demoatest.utils.CollectorsUtil;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.collections.impl.collector.BigDecimalSummaryStatistics;
import org.eclipse.collections.impl.collector.Collectors2;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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


    @Test
    void bigDecimalTest() {
        double avg = 66.665000;
        System.out.println(BigDecimal.valueOf(avg).setScale(2, BigDecimal.ROUND_HALF_UP));
        System.out.println(BigDecimal.valueOf(avg).divide(BigDecimal.valueOf(3), 2, BigDecimal.ROUND_HALF_UP));
        List<BigDecimal> list = Arrays.asList(BigDecimal.valueOf(1), BigDecimal.valueOf(2), BigDecimal.valueOf(5),
                BigDecimal.valueOf(4), BigDecimal.valueOf(3));
        final BigDecimal collect = list.stream().collect(CollectorsUtil.averagingBigDecimal(e -> e, 2, BigDecimal.ROUND_HALF_UP));
        System.out.println(collect);
        final BigDecimal collect1 = list.stream().collect(CollectorsUtil.summingBigDecimal(e -> e));
        System.out.println(collect1);
        final BigDecimal collect2 = list.stream().collect(CollectorsUtil.maxBy(e -> e));
        final BigDecimal collect3 = list.stream().collect(CollectorsUtil.minBy(e -> e));
        System.out.println(collect2 + "\t" + collect3);

        System.out.println("----------------------------------------");
        final BigDecimalSummaryStatistics collect4 = list.stream().collect(Collectors2.summarizingBigDecimal(e -> e));
        System.out.println(collect4.getAverage());

    }

    /**
     * 测试保留4位小数
     * BigDecimal.ROUND_HALF_UP 是四舍五入的方式
     */
    @Test
    void test() {
        double d = 14.15515215751;
        System.out.println(BigDecimal.valueOf(d).setScale(4, BigDecimal.ROUND_HALF_UP));
    }

}
