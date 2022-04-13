package com.yunqing.demoatest.threadpool;

import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.StampedLock;

/**
 * 比读写锁性能还要好的并发类，最适合读多写少的并发类，多线程同时读的同时，允许一个写操作。即乐观读是不加锁的操作。
 * 是 ReentrantReadWriteLock 的一个子集，不支持重入功能，不支持条件变量，调用中断方法要注意CPU飙升的问题
 * @author kangqing
 * @since 2022/4/13 20:43
 */
public class StampedLockTest {


}

/**
 * 官方例：
 */
class Point {

    private int x, y;

    final StampedLock stampedLock = new StampedLock();

    // 计算点到坐标中心的距离
    double distanceFromOrigin() {
        // 乐观读，并不加锁
        long stamp = stampedLock.tryOptimisticRead();
        // 读入共享变量，读的过程中可能会被修改
        int currentX = x;
        int currentY = y;
        // 判断读的期间是否存在写操作，
        // 如果存在写操作，则返回false
        if (!stampedLock.validate(stamp)) {
            // 升级成悲观读锁
            stamp = stampedLock.readLock();
            try {
                currentX = x;
                currentY = y;
            } finally {
                // 释放悲观读锁
                stampedLock.unlockRead(stamp);
            }
        }

        return Math.sqrt(currentX * currentX + currentY * currentY);
    }

}
