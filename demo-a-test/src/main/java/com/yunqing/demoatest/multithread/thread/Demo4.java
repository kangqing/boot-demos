package com.yunqing.demoatest.multithread.thread;

import lombok.SneakyThrows;

import java.util.concurrent.TimeUnit;

/**
 * @Description 使用join()控制线程的执行顺序
 * @Author yunqing
 * @Data 2020/7/7 22:46
 */
public class Demo4 {

    @SneakyThrows
    public static void main(String[] args) {
        Thread t1 = new Demo4Thread(1);
        Thread t2 = new Demo4Thread(2);
        Thread t3 = new Demo4Thread(3);
        t3.start();
        t3.join();
        t2.start();
        t2.join();
        t1.start();
    }

}

class Demo4Thread extends Thread {

    private int i;

    public Demo4Thread(int i) {
        this.i = i;
    }
    @SneakyThrows
    @Override
    public void run() {
        System.out.println("执行run()方法" + i);
        TimeUnit.MILLISECONDS.sleep(1000);
    }
}
