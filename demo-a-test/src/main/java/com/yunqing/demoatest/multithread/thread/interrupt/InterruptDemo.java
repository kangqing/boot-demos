package com.yunqing.demoatest.multithread.thread.interrupt;

import lombok.SneakyThrows;

import java.util.concurrent.TimeUnit;

/**
 * interrupt 中断信号
 * @author kangqing
 * @since 2021/1/20 21:57
 */
public class InterruptDemo {
    public static void main(String[] args) {
        // 线程 1 睡眠 3 秒
        Thread thread1 = new Thread() {
            @SneakyThrows
            @Override
            public void run() {
                TimeUnit.SECONDS.sleep(3);
                super.run();
            }
        };
        // 线程 2 死循环
        Thread thread2 = new Thread(() -> {
            // 死循环
            while (true) {
                //System.out.println("死循环---");
            }
        });

        thread1.start();
        thread2.start();
        // 添加中断信号
        thread1.interrupt();
        thread2.interrupt();

        System.out.println("线程1中断状态 = " + thread1.isInterrupted());
        System.out.println("线程2中断状态 = " + thread2.isInterrupted());

        System.out.println("线程1中断状态 = " + thread1.isInterrupted());
    }
}
