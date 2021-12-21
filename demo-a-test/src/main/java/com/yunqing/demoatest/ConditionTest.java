package com.yunqing.demoatest;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author kangqing
 * @since 2021/12/20 19:06
 */
public class ConditionTest {

    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        AtomicBoolean flag = new AtomicBoolean(false);

        new Thread(() -> {
            while (true) {
                if (!flag.get()) {
                    lock.lock();
                    System.out.println("我是线程1");
                    flag.set(true);
                    try {
                        condition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        lock.unlock();
                    }
                }
            }
        }, "thread1").start();

        new Thread(() -> {
            while (true) {
                if (flag.get()) {
                    lock.lock();
                    System.out.println("我是线程2");
                    flag.set(false);
                    try {
                        condition.signal();
                    } finally {
                        lock.unlock();
                    }

                }

            }
        }, "thread2").start();
    }
}
