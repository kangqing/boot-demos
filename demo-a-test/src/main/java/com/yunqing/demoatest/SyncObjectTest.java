package com.yunqing.demoatest;

import com.mysql.cj.util.TimeUtil;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author kangqing
 * @since 2021/12/20 19:20
 */
public class SyncObjectTest {
    public static void main(String[] args) {
        AtomicBoolean flag = new AtomicBoolean(false);

        Object obj = new Object();

        new Thread(() -> {
            while (true) {
                if (!flag.get()) {
                    synchronized (obj) {
                        System.out.println("111111");
                        /*try {
                            TimeUnit.SECONDS.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }*/
                        flag.set(true);
                        try {
                            obj.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                }
            }
        }).start();

        new Thread(() -> {
            while (true) {
                if (flag.get()) {
                    synchronized (obj) {
                        System.out.println("2222222");
                        flag.set(false);
                        obj.notify();
                    }

                }
            }
        }).start();
    }
}
