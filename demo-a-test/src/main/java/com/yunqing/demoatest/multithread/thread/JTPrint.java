package com.yunqing.demoatest.multithread.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 两个线程交替打印到100
 * @author kangqing
 * @since 2021/3/30 10:32
 */
public class JTPrint {

    private static int i = 1;
    private static AtomicBoolean flag = new AtomicBoolean(false);
    // 锁定对象建立成 0 长度的 byte 数组更加节约资源， 字节码只需要 3 条操作码，Object 需要 7 条
    private final static byte[] b = new byte[0];

    public static void main(String[] args) {
        //synchronizedPrint();
        lockConditionPrint();
    }


    /**
     * synchronized 交替打印 1-100
     */
    public static void synchronizedPrint() {
        new Thread(() -> {
            while (i <= 100) {
                // 锁定对象
                synchronized (b) {
                    // 先输出 1
                    if (flag.get()) {
                        try {
                            b.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else {
                        System.out.println(Thread.currentThread().getName() + "----" + i++);
                        flag.set(true);
                        b.notifyAll();
                    }
                }
            }
        }, "Thread-A").start();

        new Thread(() -> {
            while (i <= 100) {
                // 锁定对象
                synchronized (b) {
                    if (!flag.get()) {
                        try {
                            b.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else {
                        System.out.println(Thread.currentThread().getName() + "----" + i++);
                        flag.set(false);
                        b.notifyAll();
                    }
                }
            }
        }, "Thread-B").start();
    }


    /**
     * lock + condition 监视器交替打印 1-10
     */
    private static void lockConditionPrint() {
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        new Thread(() -> {
            while (i <= 10) {
                lock.lock();
                condition.signal();
                System.out.println(Thread.currentThread().getName() + i++);
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }, "Thread-A----").start();


        new Thread(() -> {
            while (i <= 10) {
                lock.lock();
                condition.signal();
                System.out.println(Thread.currentThread().getName() + i++);
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }, "Thread-B----").start();

    }
}

class TicketTask{
    public static Thread t1 = null;
    public static Thread t2 = null;
    public static void main(String[] args) {
        AtomicBoolean flag = new AtomicBoolean(true);
        t1 = new Thread(() -> {

            while (true) {
                if (flag.get()) {
                    LockSupport.park();
                } else {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("2");
                    flag.set(true);
                    LockSupport.unpark(t2);
                }
            }
        });

        t2 = new Thread(() -> {
            while (true) {
                if (!flag.get()) {

                    LockSupport.park();

                }else {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("1");
                    flag.set(false);
                    LockSupport.unpark(t1);
                }
            }
        });
        t1.start();
        t2.start();
    }
}
