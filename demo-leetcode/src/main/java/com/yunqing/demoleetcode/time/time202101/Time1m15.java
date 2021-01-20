package com.yunqing.demoleetcode.time.time202101;

import java.util.PriorityQueue;

/**
 * 1.20 优先队列----堆
 * @author kangqing
 * @since 2021/1/20 21:14
 */
public class Time1m15 {
    public static void main(String[] args) {
        Solution1m20 solution1m20 = new Solution1m20();
        System.out.println(solution1m20.maximumProduct(new int[] {-100,-98,-1,2,3,4}));
    }
}

class Solution1m20 {
    public int maximumProduct(int[] nums) {
        // 最小堆
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int i = 0; i < 3; i++) {
            queue.offer(nums[i]);
        }
        if (nums.length > 3) {
            for (int i = 3; i < nums.length; i++) {
                Integer peek = queue.peek();
                if (nums[i] > peek) {
                    queue.poll();
                    queue.offer(nums[i]);
                }
            }
        }
        // 最大堆
        PriorityQueue<Integer> maxQueue = new PriorityQueue<>((m, n) -> n - m);
        for (int i = 0; i < 2; i++) {
            maxQueue.offer(nums[i]);
        }
        for (int i = 2; i < nums.length; i++) {
            Integer peek = maxQueue.peek();
            if (nums[i] < peek) {
                maxQueue.poll();
                maxQueue.offer(nums[i]);
            }
        }
        int res1 = queue.stream().mapToInt(e -> e).reduce(1, (a, b) -> a * b);
        int res2 = maxQueue.stream().mapToInt(e -> e).reduce(1, (a, b) -> a * b);
        Integer poll = 0;
        for (int i = 0; i < 3; i++) {
            poll = queue.poll();
        }
        res2 *= poll;
        return Math.max(res1, res2);

    }
}
