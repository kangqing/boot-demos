package com.yunqing.demoatest.threadpool;

import lombok.SneakyThrows;

import java.sql.Timestamp;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * jdk1.8新特性线程
 * @author kangqing
 * @since 2022/3/24 12:29
 */
public class CompletableFutureTest {
    @SneakyThrows
    public static void main(String[] args) {
        final CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            // 执行异步业务逻辑
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread() + "执行异步逻辑>>>任务1");
            return 1;
        });

        final CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
            // 执行异步业务逻辑
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread() + "执行异步逻辑>>>任务2");
            return 4;
        });

        final CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> {
            // 执行异步业务逻辑
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread() + "执行异步逻辑>>>任务3");
            return 4;
        });

        CompletableFuture.allOf(future, future1, future2).thenRun(() -> {
            System.out.println("全部执行完成");
        });

        final CompletableFuture<Object> any = CompletableFuture.anyOf(future1, future, future2);
        System.out.println("结果>>>" + any.get());

        TimeUnit.SECONDS.sleep(3);

    }
}

/**
 * // 1.有返回值的线程
 *         // 默认使用 ForkJoinPool线程池 处理
 *         final ForkJoinPool forkJoinPool = new ForkJoinPool();
 *         // 自定义定长线程池处理
 *         final ExecutorService executorService = Executors.newFixedThreadPool(5);
 *         // 自定义线程池处理
 *         final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 15,
 *                 60, TimeUnit.SECONDS, new LinkedBlockingQueue<>());
 *         CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
 *             // 线程异步处理逻辑
 *             System.out.println("----------子线程异步执行，有返回值");
 *             return 0;
 *         }, threadPoolExecutor);
 *
 *         System.out.println("获取到返回值" + future.get());
 *
 *
 *         System.out.println("主线程结束>>>");

 *    --------------------------------------------
 */
