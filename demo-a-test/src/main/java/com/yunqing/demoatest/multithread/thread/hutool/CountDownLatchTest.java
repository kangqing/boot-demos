package com.yunqing.demoatest.multithread.thread.hutool;

import cn.hutool.core.thread.ThreadUtil;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author kangqing
 * @since 2021/3/21 09:07
 */
public class CountDownLatchTest {
    public static void main(String[] args) throws InterruptedException {
        // 相当于一个减法计数器
        final CountDownLatch countDownLatch = ThreadUtil.newCountDownLatch(5);
        System.out.println(countDownLatch.getCount());
        for (int i = 0; i < 5; i++) {
             ThreadUtil.execAsync(() -> {
                 int i1 = 0;
                 // 随机睡眠 5 秒以内
                 try {
                     i1 = new Random().nextInt(5);
                     TimeUnit.SECONDS.sleep(i1);
                 } catch (InterruptedException e) {
                     e.printStackTrace();
                 }
                 System.out.println("睡眠" + i1 + "秒" + Thread.currentThread().getName() + 
                         "线程结束" + countDownLatch.getCount());
                 // 计数器减 1
                 countDownLatch.countDown();
             });
        }
        // 等到计数器归零才会往下执行
        countDownLatch.await();
        System.out.println("计数器归零了，才执行这句话");
    }
}
