package com.yunqing.demoatest.multithread.thread;

import lombok.SneakyThrows;

import java.util.concurrent.TimeUnit;

/**
 * @Description 多线程第二种方法，实现Runnable接口
 * 1.类实现Runnable
 * 2.重写Run()方法
 * 3.实例化创建的类
 * 4.实例化Thread，构造函数传值上面声明的实例
 * 5.调用start()方法
 * @Author yunqing
 * @Data 2020/7/7 20:54
 */
public class Demo3 {

    @SneakyThrows
    public static void main(String[] args) {
        Demo3Runnable d = new Demo3Runnable();
        Thread t = new Thread(d);
        t.start();
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName() + "执行main()方法" + i);
            TimeUnit.MILLISECONDS.sleep(100);
        }
    }

}

class Demo3Runnable implements Runnable {

    @SneakyThrows
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName() + "执行run()方法" + i);
            TimeUnit.MILLISECONDS.sleep(100);
        }
    }
}
