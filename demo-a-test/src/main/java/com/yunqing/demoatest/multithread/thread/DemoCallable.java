package com.yunqing.demoatest.multithread.thread;

import lombok.SneakyThrows;

import java.util.concurrent.*;

/**
 * 多线程创建的第三种方法，实现 Callable接口
 * @author kangqing
 * @since 2021/1/19 17:21
 */
public class DemoCallable {

    @SneakyThrows
    public static void main(String[] args) {
        /**
         * 核心线程数2
         * 最大线程数10
         * 线程池中超过corePoolSize数目的空闲线程最大存活时间 30秒
         * 阻塞队列----new ArrayBlockingQueue
         */
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 100, 30L,
                TimeUnit.SECONDS, new ArrayBlockingQueue<>(10));

        final Future<Object> future = executor.submit(() -> "通过实现Callable接口,创建线程");
        String res = (String) future.get();
        System.out.println(res);


        // 复习创建方法 1：继承Thread
        method1();
        // 复习方法 2： 实现Runnable
        method2();

        // FutureTask 就是 Runnable
        FutureTask<?> task = new FutureTask<>(new MyThread());
        new Thread(task).start();
        System.out.println(task.get());
    }

    private static void method2() {
        final Thread thread = new Thread(() -> {
            System.out.println("创建多线程方法2： 实现Runnable接口");
        });
        thread.start();
    }

    private static void method1() {
        final Thread thread = new Thread() {
            @Override
            public void run() {
                System.out.println("创建多线程方法 1：继承Thread");
                super.run();
            }
        };
        thread.start();

    }


}
class MyThread implements Callable<String> {
    @Override
    public String call() throws Exception {
        System.out.println("call()");
        return "111222";
    }
}
