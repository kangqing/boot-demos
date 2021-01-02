package com.yunqing.demoleetcode.time.time202101;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 1.2 最大堆
 * @author kangqing
 * @since 2021/1/2 11:20
 */
public class Time1m2 {
    public static void main(String[] args) {
        Solution1m2 s = new Solution1m2();
        System.out.println(Arrays.toString(s.maxSlidingWindow(new int[]{1, -1}, 1)));
    }
}

class Solution1m2 {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int len = nums.length;
        int[] res = new int[len - k + 1];
        // 存储需要从划过需要删除的元素
        List<Integer> list = new ArrayList<>();
        // 默认最小堆，改造成最大堆
        PriorityQueue<Integer> queue = new PriorityQueue<>((m, n) -> n - m);
        // 前 k 个元素放进最大堆， 窗口正在显示的元素
        for (int i = 0; i < k; i++) {
            queue.offer(nums[i]);
        }
        // 慢指针从 0 开始，表示被窗口划过的元素
        int slow = 0;
        // 快指针, 从 k 开始，表示新进入窗口的元素
        int fast = k;
        // 返回数组的索引
        int index = 0;
        while (fast <= len) {
            Integer peek = queue.peek();
            while (list.contains(peek)) {
                queue.poll();
                list.remove(peek);
                peek = queue.peek();
            }
            res[index++] = peek;
            list.add(nums[slow++]);
            if (fast < len) {
                queue.offer(nums[fast++]);
            } else {
                fast++;
            }
        }
        return res;
    }
}
