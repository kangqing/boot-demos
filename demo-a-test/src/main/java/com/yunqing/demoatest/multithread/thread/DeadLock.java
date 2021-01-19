package com.yunqing.demoatest.multithread.thread;


/**
 *
 * 避免死锁的方法
 * 1.避免一个线程同时获得多个锁；
 * 2.避免一个线程在锁内部占有多个资源，尽量保证每个锁只占用一个资源；
 * 3.尝试使用定时锁，使用lock.tryLock(timeOut)，当超时等待时当前线程不会阻塞；
 * 4.对于数据库锁，加锁和解锁必须在一个数据库连接里，否则会出现解锁失败的情况
 * @author kangqing
 * @since 2021/1/19 15:29
 */
public class DeadLock {

    private static final String resource_1 = "A";
    private static final String resource_2 = "B";

    public static void main(String[] args) {
        myDeadLock();
    }

    private static void myDeadLock() {
        final Thread thread1 = new Thread(() -> {
            synchronized (resource_1) {
                System.out.println("获取 resource_1");
                try {
                    Thread.sleep(3000);
                    synchronized (resource_2) {
                        System.out.println("获取 resource_2");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        final Thread thread2 = new Thread(() -> {
            synchronized (resource_2) {
                System.out.println("获取 资源2");
                synchronized (resource_1) {
                    System.out.println("获取 资源1");
                }
            }
        });

        thread1.start();
        thread2.start();
    }
}
