package com.yunqing.demoatest.multithread.thread.hutool;

import cn.hutool.core.thread.ThreadUtil;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 计数信号量，常用于多个线程对同一资源访问，或者限流
 * 例子：
 * 一共 3 个车位， 9辆车轮流抢车位
 * @author kangqing
 * @since 2021/3/21 09:47
 */
public class SemaphoreTest {
    public static void main(String[] args) {
        // 3 个信号量 相当于一共3个车位
        Semaphore semaphore = new Semaphore(3);
        for (int i = 0; i < 9; i++) {
            final int temp = i;
            ThreadUtil.execAsync(() -> {
                Thread.currentThread().setName(temp + "线程");
                try {
                    // 抢到车位
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "抢到车位");
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println(Thread.currentThread().getName() + "离开车位-------");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    // 释放车位
                    semaphore.release();
                }
            });
        }
    }
}
