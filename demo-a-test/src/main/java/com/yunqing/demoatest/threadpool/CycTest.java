package com.yunqing.demoatest.threadpool;

import lombok.SneakyThrows;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author kangqing
 * @since 2022/4/13 21:20
 */
public class CycTest {

    public static void main(String[] args) {
        // 未优化之前
        final long s = System.currentTimeMillis();
        get3();
        final long e = System.currentTimeMillis();
        System.out.println("耗时 = " + (e - s) + "毫秒");
    }

    // 传统单线程调用
    private static void get1() {
        method_1();
        method_2();
        method_3();
    }
    // 使用 CountDownLatch 优化前两个操作并行之后
    @SneakyThrows
    private static void get2() {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        final CountDownLatch countDownLatch = new CountDownLatch(2);
        executorService.execute(() -> {
            method_1();
            countDownLatch.countDown();
        });
        executorService.execute(() -> {
            method_2();
            countDownLatch.countDown();
        });
        // 等到方法一、方法二都执行完毕才能执行方法三
        countDownLatch.await();

        method_3();

        executorService.shutdown();
    }

    // 使用CyclicBarrier优化，使方法一、方法二并行完成之后，方法三开始执行
    // ---方法一耗时---
    // ---方法二耗时---
    //                ---方法三耗时---
    private static void get3() {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        final CyclicBarrier cyclicBarrier = new CyclicBarrier(2,
                () -> {
                    method_3();
                    executorService.shutdown();
                });
        executorService.execute(() -> {
            method_1();
            try {
                cyclicBarrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        });
        executorService.execute(() -> {
            method_2();
            try {
                cyclicBarrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        });
    }

    @SneakyThrows
    private static void method_1() {
        TimeUnit.SECONDS.sleep(1);
        System.out.println("方法一执行完成");
    }

    @SneakyThrows
    private static void method_2() {
        TimeUnit.SECONDS.sleep(2);
        System.out.println("方法二执行完成");
    }

    @SneakyThrows
    private static void method_3() {
        TimeUnit.SECONDS.sleep(3);
        System.out.println("方法三执行完成");
    }
}
