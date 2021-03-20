package com.yunqing.demoatest.multithread.thread.interrupt;

import com.yunqing.demoatest.multithread.thread.ThreadPoolManager;

/**
 * @author kangqing
 * @since 2021/3/17 15:32
 */
public class Interrupt1 {
    public static void main(String[] args) {
        Thread thread = new MyThread();
        // thread 线程开始循环
        thread.start();
        for (int i = 0; i < 10000; i++) {
            // 当前线程开始循环
            System.out.println(Thread.currentThread().getName() + i);
        }
        // 中断，仅仅给线程添加一个中断标志位
        thread.interrupt();

        ThreadPoolManager poolManager = ThreadPoolManager.getThreadPoolManager();
        Runnable r = () -> {
            for (int i = 0; i < 5; i++) {
                System.out.println(11111);
                if (i > 3) {
                    poolManager.shutdown();
                }
            }
        };
        poolManager.addExecuteTask(r);
    }
}

class MyThread extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            // 真正中断靠这个判断，找到中断标记位然后 return
            if (this.isInterrupted()) {
                return;
            }
            System.out.println(Thread.currentThread().getName() + i);
        }
    }
}


