package com.yunqing.demoatest.algorithm.binarysearch;

import java.util.Arrays;

/**
 * LeetCode719 找出第 k 小的距离对
 * @author yunqing
 * @since 2020/8/22 9:00
 */
public class LeetCode719 {
    public static void main(String[] args) {
        int[] arr = new int[] {1,3,1};
        int k = 2;
        Solution719 solution719 = new Solution719();
        System.out.println(solution719.smallestDistancePair(arr, k));
    }
}

/**
 * 思路：二分法 + 快慢指针
 */
class Solution719 {
    public int smallestDistancePair(int[] nums, int k) {
        // 排序
        Arrays.sort(nums);
        // 二分法可能的最小的距离 0 ，最大距离 nums[nums.length - 1] -nums[0]
        int low = 0;
        int high = nums[nums.length - 1] - nums[0];
        while (low < high) {
            int mid = (low + high) / 2;
            // 计算nums中距离小于等于 mid 的次数
            int count = this.query_count(nums, mid);
            if (count >= k) {
                //如果小于等于mid 的距离 >= k则距离差应该在low - mid之间
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    /**
     * 计算nums中距离小于 mid 的次数
     * @param nums
     * @param mid
     * @return
     */
    private int query_count(int[] nums, int mid) {
        int count = 0;
        int slow = 0; //慢指针

        //使用快指针进行循环
        for (int fast = 1; fast <= nums.length - 1; fast++) {
            //因为是统计nums中距离小于等于mid的次数
            //所以当 > mid 的时候后面肯定全部 > mid
            while (nums[fast] - nums[slow] > mid) {
                // 慢指针加一
                slow++;
            }
            //统计 <= mid 的次数
            count += fast - slow;
        }
        return count;
    }
}
