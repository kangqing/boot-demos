package com.yunqing.demoleetcode.time;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author yunqing
 * @since 2020/12/5 11:58
 */
public class Time12m5 {
    public static void main(String[] args) {

    }
}

class Solution12m5 {
    public int leastInterval(char[] tasks, int n) {
        Map<Character, Integer> map = new HashMap<>();
        int max = 0;
        //求出最多的字符的是几个数
        for (char task : tasks) {
            int maxCount = map.getOrDefault(task, 0) + 1;
            map.put(task, maxCount);
            max = Math.max(max, maxCount);
        }

        AtomicInteger count = new AtomicInteger();
        // 求出最多数量的字符有几个
        int finalMax = max;
        map.forEach((k, v) -> {
            if (finalMax == v) {
                count.getAndIncrement();
            }
        });

        return Math.max(tasks.length, (n + 1) * (max - 1) + count.get());
    }
}
