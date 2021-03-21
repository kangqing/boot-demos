package com.yunqing.demoatest.multithread.thread.collection;

import cn.hutool.core.lang.UUID;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * java.util.ConcurrentModificationException  并发修改异常
 * @author kangqing
 * @since 2021/3/21 08:21
 */
public class HashMapTest {
    public static void main(String[] args) {
        // 线程不安全
        //Map<String, Object> map = new HashMap<>();
        // 线程安全
        //Map<String, Object> map = Collections.synchronizedMap(new HashMap<>());
        // 推荐
        Map<String, Object> map = new ConcurrentHashMap<>();

        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0, 5));
                System.out.println(map);
            }, i + "").start();
        }
    }
}
