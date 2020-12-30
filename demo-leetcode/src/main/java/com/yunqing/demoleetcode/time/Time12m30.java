package com.yunqing.demoleetcode.time;


import java.util.PriorityQueue;

/**
 * 12.30 最大堆
 * @author yx
 * @since 2020/12/30 13:55
 */
public class Time12m30 {
    public static void main(String[] args) {

    }
}

class Solution12m30 {
    public int lastStoneWeight(int[] stones) {
        // 默认最小堆，这里改造成最大堆
        PriorityQueue<Integer> queue = new PriorityQueue<>((m, n) -> n - m);
        for (int i = 0; i < stones.length; i++) {
            queue.offer(stones[i]);
        }
        while (!queue.isEmpty()) {
            final Integer poll = queue.poll();
            if (!queue.isEmpty()) {
                final Integer p = queue.poll();
                if (!p.equals(poll)) {
                    queue.offer(poll - p);
                }
            } else {
                return poll;
            }
        }
        return 0;
    }
}
