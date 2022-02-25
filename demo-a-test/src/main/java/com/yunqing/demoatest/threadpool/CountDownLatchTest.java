package com.yunqing.demoatest.threadpool;

import java.util.Vector;
import java.util.concurrent.*;

/**
 * @author kangqing
 * @since 2022/2/25 14:59
 */
public class CountDownLatchTest {

    public static void main(String[] args) {
        Vector<Integer> vector = new Vector<>();
        // 创建自定义线程池
        final ThreadPoolExecutor pool = new ThreadPoolExecutor(6, 6, 0,
                TimeUnit.SECONDS, new ArrayBlockingQueue<>(10));
        // 主线程需要等待 6 个读取线程完成数据读取之后才能继续进行统计功能
        final CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 0; i < 6; i++) {
            int finalI = i;
            pool.execute(() -> {
                try {
                    // 每个线程睡眠 1 秒模拟读取数据
                    TimeUnit.SECONDS.sleep(1);
                    vector.add(finalI);
                    System.out.println("----------" + finalI);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    // 线程完成后给同步类减一
                    countDownLatch.countDown();
                }
            });
        }
        try {
            // 主线程等待上面的 6 个线程完成之后，再继续执行
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        pool.shutdown();
        vector.forEach(System.out::println);

        completableFuture();

    }

    private static void completableFuture() {
        Vector<Integer> list = new Vector<>();
        CompletableFuture<Void> task1 = CompletableFuture.supplyAsync(()->{
            //自定义业务操作
            System.out.println("111");
            list.add(1);
            return null;
        });
        CompletableFuture<Void> task2 = CompletableFuture.supplyAsync(()->{
            //自定义业务操作
            System.out.println("222");
            list.add(2);
            return null;
        });
        CompletableFuture<Void> task3 = CompletableFuture.supplyAsync(()->{
            //自定义业务操作
            System.out.println("333");
            list.add(3);
            return null;
        });
        CompletableFuture<Void> task4 = CompletableFuture.supplyAsync(()->{
            //自定义业务操作
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("444");
            list.add(4);
            return null;
        });

        CompletableFuture<Void> future = CompletableFuture.allOf(task1, task2, task3, task4);

        try {
            future.join();
        } catch (Exception e) {
            System.out.println("出现异常");
        }

        list.forEach(System.out::println);

    }
}
