package com.yunqing.demoatest.multithread.thread;

/**
 * @Description
 * @Author yunqing
 * @Data 2020/7/6 23:15
 */
public class Demo2 {
    /**
     * 结论：并不是按照 12345的顺序输出，证明线程是随机执行的1
     * @param args
     */
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            Demo2Thread t = new Demo2Thread(i + 1);
            t.start();
        }
    }
}

class Demo2Thread extends Thread {
    private int val;
    Demo2Thread(int val) {
        this.val = val;
    }

    @Override
    public void run() {
        System.out.println("val = " + val);
    }
}
