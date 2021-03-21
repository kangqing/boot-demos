package com.yunqing.demoatest.multithread.thread.lock;

import cn.hutool.core.thread.ThreadUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁，更加细粒度的控制
 * 独占锁（写锁）：一次只能被一个线程占用
 * 共享锁（读锁）：多个线程之间不影响
 * @author kangqing
 * @since 2021/3/21 10:06
 */
public class ReadWriteLockTest {
    public static void main(String[] args) {
        MyCache myCache = new MyCache();
        for (int i = 0; i < 5; i++) {
            final int temp = i;
            ThreadUtil.execAsync(() -> {
                myCache.put(temp + "", temp + "");
            });
        }

        for (int i = 0; i < 5; i++) {
            final int temp = i;
            ThreadUtil.execAsync(() -> myCache.read(temp + ""));
        }
    }
}

class MyCache {
    private volatile Map<String, Object> map = new HashMap<>();
    // 读写锁
    ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public void put(String key, Object value) {
        // 加写锁
        readWriteLock.writeLock().lock();

        try {
            System.out.println(Thread.currentThread().getName() + "写入" + key);
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + "写入成功");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 释放写锁
            readWriteLock.writeLock().unlock();
        }

    }

    public void read(String key) {
        // 加读锁
        readWriteLock.readLock().lock();

        try {
            System.out.println(Thread.currentThread().getName() + "读取" + key);
            map.get(key);
            System.out.println(Thread.currentThread().getName() + "读取成功");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 释放读锁
            readWriteLock.readLock().unlock();
        }
    }
}
