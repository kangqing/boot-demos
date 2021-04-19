package com.yunqing.demoatest.multithread.date;

import lombok.SneakyThrows;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 4.14 守护线程，当 JVM 中所有非守护线程结束之后退出时，守护线程也会结束
 * 两个线程循环打印，使用的 synchronized 和 wait()等待、notify()通知
 * @author kangqing
 * @since 2021/4/14 21:13
 */
public class Test4_14 {
    // static Object obj = new Object();
    // 锁定对象建立成 0 长度的 byte 数组更加节约资源， 字节码只需要 3 条操作码，Object 需要 7 条
    private final static byte[] bytes = new byte[0];
    @SneakyThrows
    public static void main(String[] args) {
        // 主线程结束后，守护线程也会结束
        jiaoti();
        //TimeUnit.SECONDS.sleep(1000);
        System.out.println("主线程结束------------");
    }

    /**
     * 设置成守护线程，当所有非守护线程退出时，守护线程也会结束
     */
    private static void setDaemon() {
        Thread t1 = new Thread(() -> {
            while (true) {
                System.out.println("打印守护线程");
            }
        });
        // 设置线程 t1 是守护线程
        t1.setDaemon(true);
        t1.start();
    }


    private static void jiaoti() {
        AtomicBoolean b = new AtomicBoolean(false);
        new Thread(() -> {
            while (true) {
                synchronized (bytes) {
                    if (b.get()) {
                        System.out.println("11111");
                        b.set(false);
                        bytes.notifyAll();
                    } else {
                        try {
                            bytes.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

        }).start();

        new Thread(() -> {
            while (true) {
                synchronized (bytes) {
                    if (!b.get()) {
                        System.out.println("22222");
                        b.set(true);
                        bytes.notifyAll();
                    } else {
                        try {
                            bytes.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

        }).start();
    }
}


