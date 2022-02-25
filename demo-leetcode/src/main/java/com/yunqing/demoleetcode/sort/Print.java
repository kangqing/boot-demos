package com.yunqing.demoleetcode.sort;

import lombok.Data;

import java.util.concurrent.TimeUnit;

/**
 * @author kangqing
 * @since 2022/2/16 09:24
 */
public class Print {
    public static void main(String[] args) {
        State state = new State(false);
        new Thread(() -> {
            while (true) {
                synchronized (state) {
                    if (state.getFlag()) {
                        try {
                            state.wait();
                            TimeUnit.MILLISECONDS.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("11111");
                    state.setFlag(true);
                    state.notify();
                }
            }
        }).start();

        new Thread(() -> {
            while (true) {
                synchronized (state) {
                    if (!state.getFlag()) {
                        try {
                            state.wait();
                            TimeUnit.MILLISECONDS.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("************");
                    state.setFlag(false);
                    state.notify();
                }
            }
        }).start();
    }
}

@Data
class State {
    private Boolean flag;

    public State(Boolean flag) {
        this.flag = flag;
    }
}
