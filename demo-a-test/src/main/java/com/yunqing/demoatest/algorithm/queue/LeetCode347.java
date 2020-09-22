package com.yunqing.demoatest.algorithm.queue;

import java.util.*;

/**
 * LeetCode347 前 k 个高频元素
 * author yunqing
 * date 2020/9/7 上午6:51
 */
public class LeetCode347 {
    public static void main(String[] args) {
        Solution347 solution347 = new Solution347();
        solution347.test();
    }
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

    /**
     * map加入堆中
     */
    void test() {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(1, 1);
        map.put(2, 2);
        //声明一个最大堆，按照map.getValue()比较的最大堆
        PriorityQueue<Map.Entry<Integer, Integer>> priorityQueue = new PriorityQueue<>((o1, o2) -> o2.getValue()
                .compareTo(o1.getValue()));
        //map加入优先队列
        for (Map.Entry<Integer, Integer> integerIntegerEntry : map.entrySet()) {
            priorityQueue.offer(integerIntegerEntry);
        }
        Map.Entry<Integer, Integer> poll = priorityQueue.poll();
        System.out.println(poll.getValue());
    }
}
