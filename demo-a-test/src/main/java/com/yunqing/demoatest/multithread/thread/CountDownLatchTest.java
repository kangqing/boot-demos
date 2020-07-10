package com.yunqing.demoatest.multithread.thread;

import lombok.SneakyThrows;

import java.util.concurrent.CountDownLatch;

/**
 * @Description
 * @Author yunqing
 * @Data 2020/7/10 14:48
 */
public class CountDownLatchTest {

    @SneakyThrows
    public static void main(String[] args) {

        Foo foo = new Foo();
        Thread t1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName());
            try {
                foo.first(() -> {
                    System.out.println("one");
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread t2 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName());
            try {
                foo.second(() -> {
                    System.out.println("two");
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread t3 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName());
            try {
                foo.third(() -> {
                    System.out.println("three");
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t1.setName("A");
        t2.setName("B");
        t3.setName("C");
        t3.start();
        t1.start();
        t2.start();



    }

}

/**
 * 保证无论线程顺序怎么执行
 * 调用方法的顺序都是 first -> second -> third
 */

class Foo {
    private CountDownLatch latch2;
    private CountDownLatch latch3;

    Foo() {
        latch2 = new CountDownLatch(1);
        latch3 = new CountDownLatch(1);
    }

    void first(Runnable printFirst) throws InterruptedException {

        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        latch2.countDown();
    }

    void second(Runnable printSecond) throws InterruptedException {
        latch2.await();
        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
        latch3.countDown();
    }

    void third(Runnable printThird) throws InterruptedException {
        latch3.await();
        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
    }
}
