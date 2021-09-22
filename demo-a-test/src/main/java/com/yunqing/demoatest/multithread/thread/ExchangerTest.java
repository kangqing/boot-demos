package com.yunqing.demoatest.multithread.thread;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;

/**
 * 交换对象的通道
 * @author kangqing
 * @since 2021/9/16 21:11
 */
public class ExchangerTest {
    public static void main(String[] args) {
        final Exchanger<String> exchanger = new Exchanger<>();
        new Thread(() -> {
            trade("清华大学", "博士", exchanger);
        }).start();
        new Thread(() -> {
            trade("北京大学", "硕士", exchanger);
        }).start();
        new Thread(() -> {
            trade("南开大学", "总理", exchanger);
        }).start();
        new Thread(() -> {
            trade("天津大学", "医生", exchanger);
        }).start();
    }

    static void trade(String team, String person, Exchanger<String> exchanger) {
        try {
            System.out.println(Thread.currentThread().getId() + team + "转让" + person);
            TimeUnit.SECONDS.sleep(3);
            String exchange = exchanger.exchange(person);
            System.out.println(Thread.currentThread().getId() + team + "得到了" + exchange);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


