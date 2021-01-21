package com.yunqing.demoatest.multithread.thread.interrupt;

import lombok.SneakyThrows;

import java.util.concurrent.TimeUnit;

/**
 * 最后总结，关于这三个方法，interrupt（）是给线程设置中断标志；interrupted（）是检测中断并清除中断状态；isInterrupted（）只检测中断。
 * 还有重要的一点就是interrupted（）作用于当前线程，interrupt（）和isInterrupted（）作用于此线程，即代码中调用此方法的实例所代表的线程。
 * interrupt 中断信号
 * @author kangqing
 * @since 2021/1/20 21:57
 */
public class InterruptDemo {
    @SneakyThrows
    public static void main(String[] args) {

        for (int i = 0; i < 3; i++) {
            TimeUnit.SECONDS.sleep(1);
            System.out.println("主线程" + Thread.currentThread().getName() + "输出 = " + i);
        }

        Thread thread = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println("输出 = " + i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    System.out.println("-----------------------------------------------------输出 = " + i);
                }
            }
        });

        thread.start();

        //TimeUnit.SECONDS.sleep(3);
        System.out.println("仅输出中断状态 = " + thread.isInterrupted());
        System.out.println("仅输出中断状态 = " + thread.isInterrupted() + "两次输出证明没有改变中断状态");
        // 中断，相当于添加中断标志位，不影响程序继续输出
        thread.interrupt();
        Thread.currentThread().interrupt();

        // 主线程输出中断状态并删除中断状态, interrupted()操作的是当前线程
        final boolean interrupted = Thread.interrupted();
        System.out.println("------------------" + interrupted);
        System.out.println("------------------" + Thread.interrupted());

        System.out.println("最后再次打印仅输出中断状态" + thread.isInterrupted());
        System.out.println("主线程中断状态" + Thread.currentThread().isInterrupted());


    }

}
