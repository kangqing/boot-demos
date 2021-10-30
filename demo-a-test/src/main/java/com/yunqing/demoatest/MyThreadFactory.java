package com.yunqing.demoatest;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.ThreadFactory;

/**
 * 自定义线程工厂
 * @author kangqing
 * @since 2021/10/30 12:56
 */
public class MyThreadFactory implements ThreadFactory {
    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r);
        t.setUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
        return t;
    }
}

/**
 * 统一线程池异常处理器
 */
@Slf4j
class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        log.error(e.getMessage(), e);
    }
}



class MyTaskCall implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        int ran = (int) (Math.random() * 10);
        if (ran > 8) {
            throw new RuntimeException("test..." + ran);
        }
        System.out.println(Thread.currentThread().getId() + " running..." + ran);
        return ran;
    }
}
