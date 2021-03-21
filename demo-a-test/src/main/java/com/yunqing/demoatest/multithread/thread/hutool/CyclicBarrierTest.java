package com.yunqing.demoatest.multithread.thread.hutool;

import cn.hutool.core.thread.ThreadUtil;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 加法计数器
 * @author kangqing
 * @since 2021/3/21 09:35
 */
public class CyclicBarrierTest {
    public static void main(String[] args) {
        // 相当于集齐了 7 个线程 则执行召唤神龙的操作
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7 , () -> System.out.println("召唤神龙成功"));
        for (int i = 0; i < 7; i++) {
            ThreadUtil.execAsync(() -> {
                System.out.println(Thread.currentThread().getName());
                try {
                    // 等待条件完成
                    cyclicBarrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
