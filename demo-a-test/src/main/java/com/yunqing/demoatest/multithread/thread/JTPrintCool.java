package com.yunqing.demoatest.multithread.thread;

import java.util.concurrent.TimeUnit;

/**
 * 交替打印
 * @author kangqing
 * @since 2021/9/9 21:25
 */
public class JTPrintCool {
    public static void main(String[] args) {
        State state = new State();
        new Thread(() -> {
            while (true) {
                try {
                    synchronized (state) {
                        if (state.flag) {
                            state.wait();
                        }
                        System.out.println("111");
                        TimeUnit.SECONDS.sleep(1);
                        state.flag = true;
                        state.notify();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();


        new Thread(() -> {
            while (true) {
                try {
                    synchronized (state) {
                        if (!state.flag) {
                            state.wait();
                        }
                        System.out.println("222");
                        TimeUnit.SECONDS.sleep(1);
                        state.flag = false;
                        state.notify();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}

class State {
    public boolean flag = false;
}
