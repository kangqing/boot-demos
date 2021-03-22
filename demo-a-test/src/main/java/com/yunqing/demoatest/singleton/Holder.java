package com.yunqing.demoatest.singleton;

/**
 * 静态内部类单例
 * @author kangqing
 * @since 2021/3/22 08:43
 */
public class Holder {
    private Holder() {}

    private static class SingletonHolder {
        private final static Holder instance = new Holder();
    }

    public static Holder getInstance() {
        return SingletonHolder.instance;
    }
}
