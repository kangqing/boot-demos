package com.yunqing.demoatest.multithread.thread.lock;

import cn.hutool.core.thread.ThreadUtil;
import lombok.SneakyThrows;

import java.util.concurrent.TimeUnit;

/**
 * 8 锁 ===> 关于 8 个锁的问
 * @author kangqing
 * @since 2021/3/20 20:06
 */
public class Lock8_1Test {
    @SneakyThrows
    public static void main(String[] args) {
        Phone1 phone1 = new Phone1();
        // 发短信和打电话是并发的，谁先抢到锁，先执行谁，锁的是 phone1 对象
        for (int i = 0; i < 1000; i++) {
            ThreadUtil.execAsync(phone1::call);
        }
        ThreadUtil.execAsync(phone1::sendMessage);

        // 睡眠 1 秒
        TimeUnit.SECONDS.sleep(1);
        // 发微信永远在发短信前输出，因为就算是发短信抢到了最新的 phone1 的锁，也是睡眠 4 秒输出
        // 而只需要睡眠 1 秒，另一个线程进来 需要抢的锁和打电话、打短信不是一个锁
        ThreadUtil.execAsync(Phone1::weixin);

    }
}

class Phone1 {

    /**
     * synchronized 锁的是方法的调用者
     * 谁调用这个方法就锁谁
     */
    @SneakyThrows
    public synchronized void sendMessage() {
        TimeUnit.SECONDS.sleep(4);
        System.out.println("发短信-------------------------");
    }

    public synchronized void call() {
        System.out.println("打电话");
    }

    /**
     * 静态方法锁的是 Phone1.class 类
     */
    public static synchronized void weixin() {
        System.out.println("发微信===========");
    }
}
