package com.yunqing.demoatest.multithread.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Description
 * @Author yunqing
 * @Data 2020/7/9 22:21
 */
public class ThreadPool {



    public static void main(String[] args) {
        /**
         * 核心线程数2
         * 最大线程数10
         * 线程池中超过corePoolSize数目的空闲线程最大存活时间 30秒
         * 阻塞队列----new ArrayBlockingQueue
         */
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 100, 30L,
                TimeUnit.SECONDS, new ArrayBlockingQueue<>(10));

        final CountDownLatch latch = new CountDownLatch(5);

        for (int i = 0; i < 5; i++) {
            Runnable runnable = () -> {
                System.out.println("子线程" + Thread.currentThread().getName() + "开始执行");
                try {
                    TimeUnit.MILLISECONDS.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("子线程" + Thread.currentThread().getName() + "执行完毕");
                latch.countDown();//执行减一操作
            };
            executor.execute(runnable);
        }

        try {
            System.out.println("主线程"+Thread.currentThread().getName()+"等待子线程执行完成...");
            latch.await();//阻塞当前线程，直到计数器的值为0
            System.out.println("主线程"+Thread.currentThread().getName()+"开始执行...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


}
