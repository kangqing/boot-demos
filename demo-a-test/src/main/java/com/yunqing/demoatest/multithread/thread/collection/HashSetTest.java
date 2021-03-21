package com.yunqing.demoatest.multithread.thread.collection;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.thread.ThreadUtil;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * java.util.ConcurrentModificationException
 * @author kangqing
 * @since 2021/3/21 08:08
 */
public class HashSetTest {

    public static void main(String[] args) {
        // 线程不安全
        //Set<String> set = new HashSet<>();
        // 线程安全
        //Set<String> set = Collections.synchronizedSet(new HashSet<>());
        Set<String> set = new CopyOnWriteArraySet<>();
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                set.add(UUID.randomUUID().toString().substring(0, 5));
                System.out.println(set);
            }, i + "").start();
        }
    }
}
