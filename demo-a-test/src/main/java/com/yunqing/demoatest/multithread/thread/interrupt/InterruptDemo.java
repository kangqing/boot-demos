package com.yunqing.demoatest.multithread.thread.interrupt;

import lombok.SneakyThrows;

import java.util.concurrent.TimeUnit;

/**
 * 最后总结，关于这三个方法，
 * interrupt（）是给线程设置中断标志；
 * interrupted（）是检测中断并清除中断状态；(仅作用于当前线程)
 * isInterrupted（）只检测中断。
 * 还有重要的一点就是interrupted（）作用于当前线程，interrupt（）和isInterrupted（）作用于此线程，即代码中调用此方法的实例所代表的线程。
 * interrupt 中断信号 默认值是 false
 * @author kangqing
 * @since 2021/1/20 21:57
 */
public class InterruptDemo {

    private static boolean flag = false;

    @SneakyThrows
    public static void main(String[] args) {

        System.out.println("主线程验证中断标记位 = " + Thread.currentThread().isInterrupted());
        // 设置为 true
        Thread.currentThread().interrupt();
        System.out.println("主线程验证中断标记位 = " + Thread.currentThread().isInterrupted());
        // 注意直接使用 Thread 调用，因为仅仅作用于当前线程
        // 注意此方法输出当前中断标记位状态之后，会恢复中断标记位为 false
        System.out.println("检测并清除中断标记位，仅作用于当前线程，标记位 = " + Thread.interrupted());
        System.out.println("主线程验证中断标记位 = " + Thread.currentThread().isInterrupted());

        Thread thread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println("------");
            }
            System.out.println("thread线程结束");
        });
        thread.start();

        TimeUnit.MILLISECONDS.sleep(1);
        thread.interrupt();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("线程当前的状态是 = " + thread.getState());


        // 验证全局标志位结束线程

        Thread thread1 = new Thread(() -> {
            while (!flag) {
                System.out.println("^^^^^^^^^^^");
            }
            System.out.println("thread1线程结束");
        });
        thread1.start();
        TimeUnit.MILLISECONDS.sleep(1);
        // 修改全局变量结束
        flagEnd();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("thread1线程状态 = " + thread1.getState());

    }

    /**
     * 线程全局标志位结束
     */
    public static void flagEnd() {

        flag = true;

    }

}
