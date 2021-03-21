package com.yunqing.demoatest.multithread.thread.queue;

import cn.hutool.core.thread.ThreadUtil;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * 同步队列
 * 里面最多存储一个东西，put 进去一个必须 take 取出来之后才能继续 put
 * @author kangqing
 * @since 2021/3/21 13:43
 */
public class SynchronousQueueTest {
    public static void main(String[] args) {
        BlockingQueue<String> queue = new SynchronousQueue<>();
        ThreadUtil.execAsync(() -> {
            try {
                queue.put(Thread.currentThread().getName() + " 1");
                System.out.println(Thread.currentThread().getName() + " = 1");
                queue.put(Thread.currentThread().getName() + " 2");
                System.out.println(Thread.currentThread().getName() + " = 2");
                queue.put(Thread.currentThread().getName() + " 3");
                System.out.println(Thread.currentThread().getName() + " = 3");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        ThreadUtil.execAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
                System.out.println("========  " + queue.take());
                TimeUnit.SECONDS.sleep(2);
                System.out.println("========  " + queue.take());
                TimeUnit.SECONDS.sleep(2);
                System.out.println("========  " + queue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}
