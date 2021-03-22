package com.yunqing.demoatest.multithread.thread.volatiletest;

import cn.hutool.core.thread.ThreadUtil;
import lombok.SneakyThrows;

import java.util.concurrent.TimeUnit;

/**
 * 1. 保证可见性
 * @author kangqing
 * @since 2021/3/21 20:48
 */
public class VolatileTest {

    /**
     * 加上 volatile 之后是可见的了，就能停止死循环
     */
    private volatile static Integer NUM = 0;
    @SneakyThrows
    public static void main(String[] args) {
        new Thread(() -> {
            while (NUM == 0) {
                // 死循环
            }
        }, "A").start();

        TimeUnit.SECONDS.sleep(2);
        System.out.println("如果主线程修改之后，主存中的值对线程A可见，就会停止死循环");
        NUM = 1;

    }
}
