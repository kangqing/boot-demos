package com.yunqing.demoatest.multithread.thread;

import java.util.concurrent.TimeUnit;

/**
 * 继承Thread实现多线程，demo1
 * @author yunqing
 * @since 2020/7/6 23:00
 */
public class Demo1 {

    /**
     * 结论：
     * 多线程中，代码的执行结果与代码的执行顺序或者调用顺序无关。
     * 原因线程是一个子任务，CPU会以一种不确定的方式随机调用线程中的run()方法
     * @param args
     */
    public static void main(String[] args) {
        Demo1Thread t = new Demo1Thread();
        t.start();
        for (int i = 0; i < 10; i++) {
            System.out.println("执行main()方法" + i);
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}

class Demo1Thread extends Thread {
    /**
     * 1.继承Thread类
     * 2.重写run方法
     * 3.实例化
     * 4.调用start()方法
     */
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("执行run()方法" + i);
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
