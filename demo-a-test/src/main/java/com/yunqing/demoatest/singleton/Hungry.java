package com.yunqing.demoatest.singleton;

/**
 * 饿汉模式单例
 * @author kangqing
 * @since 2021/3/22 07:43
 */
public class Hungry {
    // 饿汉模式，上来就初始化
    private static Hungry hungry = new Hungry();

    /**
     * 私有化构造器，防止外部new
     */
    private Hungry() {}

    public static Hungry getInstance() {
        return hungry;
    }



}
