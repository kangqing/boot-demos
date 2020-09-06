package com.yunqing.demoatest.algorithm.queue;

import java.util.*;

/**
 * LeetCode347 前 k 个高频元素
 * author yunqing
 * date 2020/9/7 上午6:51
 */
public class LeetCode347 {
}

/**
 * 堆的思想，PriorityQueue无界优先队列，默认最小堆，可自定义
 */
class Solution347 {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        /**
         * 无界优先队列，最小堆，默认指定值最小排在前面
         * 这里改造成最大值在前面
         */
        PriorityQueue<int[]> queue = new PriorityQueue<>((o1, o2) -> o2[1] - o1[1]);
        map.forEach((key, value) -> {
            queue.offer(new int[]{key, value});
        });
        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = Objects.requireNonNull(queue.poll())[0];
        }
        return res;
    }
}
