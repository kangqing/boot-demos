package com.yunqing.demoatest.multithread.thread;

import lombok.Data;

/**
 * @Description 暂停线程
 * @Author yunqing
 * @Data 2020/7/8 22:30
 */
public class SuspendThreadTest {

    public static void main(String[] args) {
        SuspendThreadService test = new SuspendThreadService();
        Thread t1 = new Thread(test::method);
        t1.setName("A");
        t1.start();

        Thread t3 = new Thread(test::method);
        t3.setName("B");
        t3.start();

        Thread t2 = new Thread(test::method);
        t2.start();

    }

}

/**
 * 暂停线程服务，演示为什么suspend()方法被弃用
 */
class SuspendThreadService {

    synchronized void method() {
        System.out.println("线程开始");
        if ("A".equals(Thread.currentThread().getName())) {
            System.out.println("线程被暂停");
            Thread.currentThread().suspend();
        } else if ("B".equals(Thread.currentThread().getName())) {
            System.out.println("线程被恢复");
            Thread.currentThread().resume();
        }
        System.out.println("线程结束");
    }
}
