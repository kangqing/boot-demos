package com.yunqing.demoatest.deadlock;

import java.util.ArrayList;
import java.util.List;

/**
 * 单例锁类,相当于转账时候的管理员，必须获取转入转出两个锁，才能进行转账
 * @author kangqing
 * @since 2022/4/1 08:08
 */
public class Allocator {

    // 需要获取转入转入两个账户才能进行转账
    private final List<Account> locks = new ArrayList<>();

    public synchronized void apply(Account src, Account tag){
        while (locks.contains(src) || locks.contains(tag)) {
            try {
                this.wait();
            } catch (InterruptedException e) {

            }
        }
        locks.add(src);
        locks.add(tag);
    }
    // 完成转账，释放锁，并且通知其他线程进行抢锁
    public synchronized void release(Account src, Account tag){
        locks.remove(src);
        locks.remove(tag);
        this.notifyAll();
    }
    // 静态内部类单例
    private Allocator(){}

    public static Allocator getInstance(){
        return AllocatorSingle.install;
    }
    private static class AllocatorSingle{
        public final static Allocator install = new Allocator();
    }
}
