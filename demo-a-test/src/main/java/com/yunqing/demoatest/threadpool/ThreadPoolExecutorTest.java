package com.yunqing.demoatest.threadpool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 底层运用最广泛的线程池
 * @author kangqing
 * @since 2021/9/23 20:56
 */
public class ThreadPoolExecutorTest {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Runnable> blockingQueue = new LinkedBlockingQueue<>(5);
        final ThreadPoolExecutor pool =
                new ThreadPoolExecutor(3, 8, 2000,
                        TimeUnit.MILLISECONDS, blockingQueue);
        // 任务数设置为 13
        for (int i = 0; i < 13; i++) {
            pool.execute(() -> {
                System.out.println(Thread.currentThread().getId() + " is running...");
                try {
                    TimeUnit.MILLISECONDS.sleep(800);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        // 观察线程池中线程数量
        System.out.println("此时线程池中工作线程的数量 = " + pool.getPoolSize());

        TimeUnit.SECONDS.sleep(4);

        System.out.println("此时线程池中工作线程的数量 = " + pool.getPoolSize());

        pool.shutdown();

        TimeUnit.SECONDS.sleep(1);

        System.out.println("此时线程池中工作线程的数量 = " + pool.getPoolSize());
    }
}
