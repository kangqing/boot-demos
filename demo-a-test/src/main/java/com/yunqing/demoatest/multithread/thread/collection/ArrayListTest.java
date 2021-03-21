package com.yunqing.demoatest.multithread.thread.collection;

import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.IdUtil;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 多线程下 ArrayList 线程不安全 java.util.ConcurrentModificationException
 * 解决办法：
 * 1. Vector
 * 2. Collections.synchronizedList(new ArrayList<>())
 * 3. CopyOnWriteArrayList
 * @author kangqing
 * @since 2021/3/20 20:55
 */
public class ArrayListTest {
    public static void main(String[] args) {
        //List<String> list = new ArrayList<>();
        //List<String> list = new Vector<>();
        //List<String> list = Collections.synchronizedList(new ArrayList<>());
        List<String> list = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 5));
                System.out.println(list);
            }).start();
        }
    }
}
