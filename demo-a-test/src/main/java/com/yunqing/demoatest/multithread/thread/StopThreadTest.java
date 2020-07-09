package com.yunqing.demoatest.multithread.thread;

import lombok.SneakyThrows;

import java.util.concurrent.TimeUnit;

/**
 * @Description
 * interrupt(): 实例方法。它通知目标线程中断，也就是设置中断标志位(中断标志位表示当前线程已经被中断了)。
 * isInterrupted()：实例方法。用来判断当前线程是否有被中断(通过检查中断标志位)
 * interrupted()：静态方法。用来判断当前线程的中断状态，并清除当前线程的中断标志位状态。
 * @Author yunqing
 * @Data 2020/7/8 20:54
 */
public class StopThreadTest {

    @SneakyThrows
    public static void main(String[] args) {
        StopThread1 t = new StopThread1();
        t.start();
        t.stopMethod();
        System.out.println("--------------------------------------------------");
        StopThread2 stopThread2 = new StopThread2();
        Thread t2 = new Thread(stopThread2);
        t2.start();
        TimeUnit.MILLISECONDS.sleep(1000);
        t2.interrupt();
        t2.interrupt();
    }
}

/**
 * 停止线程的两种方法
 * 1.自己设置一个标记位
 * 2.利用interrupt()做停止标记
 */
class StopThread1 extends Thread {

    private boolean flag = true;

    @SneakyThrows
    @Override
    public void run() {
        if (flag) {
            System.out.println("执行run()方法");
            TimeUnit.MILLISECONDS.sleep(1000);
        }
    }

    /**
     * 停止方法
     */
    void stopMethod() {
        System.out.println("调用此自定义方法停止线程");
        flag = false;
    }
}

class StopThread2 implements Runnable {

    @SneakyThrows
    @Override
    public void run() {
        while (true) {
            if (Thread.interrupted()) {
                System.out.println("Someone interrupted me.");
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("中断标志位没被清除");
                }
                break;
            } else {
                System.out.println("this thread is running...");
            }
        }
    }
}
