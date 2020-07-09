package com.yunqing.demoatest.multithread.thread;

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

        Thread t2 = new Thread(test::method);
        t2.start();

    }

}

/**
 * 暂停线程服务，演示为什么suspend()方法被弃用
 */
class SuspendThreadService {

    /**
     * 加上同步锁之后，暂停线程就不能释放锁，导致一直处于线程暂停状态
     */
    synchronized void method() {
        System.out.println("线程开始");
        if ("A".equals(Thread.currentThread().getName())) {
            System.out.println("线程被暂停");
            Thread.currentThread().suspend();
        }
        System.out.println("线程结束");
    }
}
