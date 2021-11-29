package com.yunqing.demoatest.threadpool;

import javax.sound.sampled.FloatControl;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 可重入锁实例
 * @author kangqing
 * @since 2021/10/30 20:03
 */
public class ReentrantLockTest {
    public static void main(String[] args) {
        ReentrantLockTest test = new ReentrantLockTest();
        test.m1();
    }

    private final ReentrantLock lock = new ReentrantLock();

    public void m1() {
        System.out.println("m1方法加锁之前，当前持有锁数量 = " + lock.getHoldCount());
        lock.lock();
        try {
            System.out.println("m1方法加锁之后，当前持有锁数量 = " + lock.getHoldCount());
            m2();
        } finally {
            lock.unlock();
            System.out.println("m1方法解锁之后，当前持有锁数量 = " + lock.getHoldCount());
        }

    }

    private void m2() {
        lock.lock();
        try {
            System.out.println("m2方法加锁之后，持有锁数量 = " + lock.getHoldCount());
        } finally {
            lock.unlock();
            System.out.println("m2方法解锁之后，持有锁数量 = " + lock.getHoldCount());
        }
    }
}


