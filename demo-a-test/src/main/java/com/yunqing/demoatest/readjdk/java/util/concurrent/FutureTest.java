package com.yunqing.demoatest.readjdk.java.util.concurrent;

import lombok.SneakyThrows;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * 异步回调
 * @author kangqing
 * @since 2021/3/21 19:44
 */
public class FutureTest {
    @SneakyThrows
    public static void main(String[] args) {
        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName() + "runAsync()");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println("---------------");
        // 获取阻塞执行结果
        completableFuture.get();


        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("有返回结果的");
            return 1024;
        });

        System.out.println(future.whenComplete((t, u) -> {
            System.out.println("t ==》" + t);
            System.out.println("u ==>" + u);
        }).exceptionally(e -> {
            System.out.println(e.getMessage());
            return 23333;
        }).get());
    }
}
