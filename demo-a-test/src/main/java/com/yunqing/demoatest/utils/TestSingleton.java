package com.yunqing.demoatest.utils;

/**
 * 单例模式
 * @author kangqing
 * @since 2021/3/13 05:38
 */
public class TestSingleton {
}
// 双重锁检验，线程安全
class Singleton1 {
    private volatile static Singleton1 singleton1;
    private Singleton1(){}
    public static Singleton1 getSingleton1() {
        // 先判断是否实例化，没有才进入锁代码
        if (singleton1 == null) {
            // 类对象加锁
            synchronized (Singleton1.class) {
                // 二次判断
                if (singleton1 == null) {
                    singleton1 = new Singleton1();
                }
            }
        }
        return singleton1;
    }
}

// 静态内部类，线程安全
class Singleton2 {
    private static class SingletonHolder {
        private static final Singleton2 singleton2 = new Singleton2();
    }
    private Singleton2() {}

    public static Singleton2 getSingleton2() {
        return SingletonHolder.singleton2;
    }
    
}

// 饿汉模式，线程安全
class Singleton3 {
    // 类加载时就初始化，防止了线程同步问题，天然线程安全
    private static Singleton3 singleton3 = new Singleton3();
    private Singleton3() {}

    public static Singleton3 getSingleton3() {
        return singleton3;
    }
}

// 懒汉模式，线程不安全
class Singleton4 {
    private static Singleton4 singleton4;
    private Singleton4() {}
    public static Singleton4 getSingleton4() {
        if (singleton4 == null) {
            singleton4 = new Singleton4();
        }
        return singleton4;
    }
}
