package com.yunqing.demoatest.multithread.date;

import lombok.SneakyThrows;

import java.util.concurrent.TimeUnit;

/**
 * 4.15
 * @author kangqing
 * @since 2021/4/15 20:15
 */
public class Test4_15 {
    public static void main(String[] args) throws InterruptedException {
        MyThread t = new MyThread();
        t.start();
        TimeUnit.SECONDS.sleep(4);
        t.setStopped(); // 通知线程关闭
        t.join(); // 是主线程等待子线程的终止。也就是说主线程的代码块中，// 如果碰到了t.join()方法，
        // 此时主线程需要等待（阻塞），等待子线程结束了(Waits for this thread to die.),才能继续执行t.join()之后的代码块。
        System.out.println("123456");
    }
}

class MyThread extends Thread {
    private boolean stopped = false;
    @Override
    public void run() {
        while(!stopped) {
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println("----------");
                TimeUnit.SECONDS.sleep(1);
                System.out.println("----------");
                TimeUnit.SECONDS.sleep(1);
                System.out.println("----------");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void setStopped() {
        this.stopped = true;
    }
}
