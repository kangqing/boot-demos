package com.yunqing.demoatest.multithread.thread.lock;

import java.util.concurrent.TimeUnit;

/**
 * 模拟银行叫号
 * @author kangqing
 * @since 2023/7/5 07:36
 */
public class TicketIDTest {
    public static void main(String[] args) {

        State obj = new State();
        new Thread(() -> {
            synchronized (obj) {
                for (int i = 1; i <= 10; i += 2) {
                    if (obj.flag == 1) {
                        try {
                            obj.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("打印===>" + i);
                    try {
                        obj.flag = 1;
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    obj.notify();
                }
            }
        }).start();

        new Thread(() -> {
            synchronized (obj) {
                for (int i = 2; i <= 10 ; i+=2) {
                    if (obj.flag == 0) {
                        try {
                            obj.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("打印===>" + i);
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    obj.flag = 0;
                    obj.notify();

                }
            }
        }).start();
    }
}

class State {
    public int flag = 0;
}
