package com.yunqing.demoatest.multithread.thread;

import lombok.SneakyThrows;

import java.util.concurrent.TimeUnit;

/**
 * 多线程第二种方法，实现Runnable接口
 * 1.类实现Runnable
 * 2.重写Run()方法
 * 3.实例化创建的类
 * 4.实例化Thread，构造函数传值上面声明的实例
 * 5.调用start()方法
 * @author  yunqing
 * @since 2020/7/7 20:54
 */
public class Demo3 implements Runnable{

    static int i = 0;

    @SneakyThrows
    public static void main(String[] args) {
        Demo3 d = new Demo3();
        Thread t1 = new Thread(d);
        Thread t2 = new Thread(d);
        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println("------" + i);
    }

    @Override
    public void run() {
        for (int j = 0; j < 10; j++) {
            i++;
            System.out.println(Thread.currentThread().getName() + "###########" + i);
        }
    }
}
