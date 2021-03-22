package com.yunqing.demoatest.singleton;

import cn.hutool.core.thread.ThreadUtil;
import lombok.SneakyThrows;

import java.lang.reflect.Constructor;

/**
 * 懒汉模式
 * @author kangqing
 * @since 2021/3/22 07:47
 */
public class Lazy {
    // volatile 禁止指令重排
    private volatile static Lazy lazy;

    private Lazy() {
        System.out.println(Thread.currentThread().getName() + "is_ok");
    }

    /**
     * 双重锁检测
     * @return
     */
    public static Lazy getInstance() {
        if (lazy == null) {
            synchronized (Lazy.class) {
                if (lazy == null) {
                    lazy = new Lazy();  // 不是原子性操作，可能发生指令重排
                                        // 1.分配内存空间
                                        // 2.执行构造方法，初始化对象
                                        // 3.把对象指向内存空间地址
                }
            }
        }
        return lazy;
    }


    /**
     * 懒汉模式多线程下的问题
     * @param args
     */
    @SneakyThrows
    public static void main(String[] args) {
        /*for (int i = 0; i < 10; i++) {
            new Thread(Lazy::getInstance).start();
        }*/

        // 反射可以破坏这种单例，不能破坏枚举单例
        Lazy instance = Lazy.getInstance();
        Constructor<Lazy> constructor = Lazy.class.getDeclaredConstructor(null);
        constructor.setAccessible(true);
        Lazy instance1 = constructor.newInstance();
        System.out.println(instance1);
        System.out.println(instance);
    }
}
