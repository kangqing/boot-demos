package com.yunqing.demoatest.multithread.thread.lock;

import cn.hutool.core.thread.ThreadUtil;
import lombok.SneakyThrows;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用 JUC 包下 Lock 锁代替 synchronized
 * 写一个生产者消费者 +1  -1 的多线程操作，     等待；业务；通知
 * @author kangqing
 * @since 2021/3/20 16:38
 */
public class LockTest {
    private final static Integer CPU_NUM = Runtime.getRuntime().availableProcessors();
    public static void main(String[] args) {
        //  创建新的线程池
        ThreadPoolExecutor threadPoolExecutor = ThreadUtil.newExecutor(CPU_NUM, CPU_NUM * 2);
        //NumSyncVersion nsv = new NumSyncVersion();
        NumLockVersion nsv = new NumLockVersion();

        Runnable r1 = () -> {
            for (int i = 0; i < 10; i++) {
                nsv.increment();
            }
        };
        Runnable r2 = () -> {
            for (int i = 0; i < 10; i++) {
                nsv.decrement();
            }
        };
        Runnable r3 = () -> {
            for (int i = 0; i < 10; i++) {
                nsv.increment();
            }
        };
        Runnable r4 = () -> {
            for (int i = 0; i < 10; i++) {
                nsv.decrement();
            }
        };
        threadPoolExecutor.execute(r2);
        threadPoolExecutor.execute(r1);
        threadPoolExecutor.execute(r3);
        threadPoolExecutor.execute(r4);

    }
}

// Num 类 synchronized 版本
class NumSyncVersion {
    private int number = 0;

    @SneakyThrows
    public synchronized void increment() {
        // 这块必须用while 如果if 会出现虚假唤醒的问题
        while (number != 0) {
            // 等待
            this.wait();
        }
        // 业务
        number++;
        System.out.println(Thread.currentThread().getName() + "--->" + number);
        // 通知
        this.notifyAll();
    }

    @SneakyThrows
    public synchronized void decrement() {
        while (number != 1) {
            this.wait();
        }
        number--;
        System.out.println(Thread.currentThread().getName() + "--->" + number);
        this.notifyAll();
    }
}

class NumLockVersion {
    private int number = 0;
    Lock lock = new ReentrantLock();
    // 监视器，可以实现精准通知，建立多个监视器，实现精准通知某个监视器唤醒
    Condition condition = lock.newCondition();
    public void increment() {
        // 上锁
        lock.lock();
        try {
            // 判断等待
            while (number != 0) {
                condition.await();
            }
            // 业务
            number++;
            System.out.println(Thread.currentThread().getName() + "--->" + number);
            // 通知
            condition.signalAll();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 解锁
            lock.unlock();
        }
    }


    public void decrement() {
        lock.lock();

        try {
            while (number != 1) {
                condition.await();
            }
            number--;
            System.out.println(Thread.currentThread().getName() + "--->" + number);
            condition.signalAll();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }


    }
}
