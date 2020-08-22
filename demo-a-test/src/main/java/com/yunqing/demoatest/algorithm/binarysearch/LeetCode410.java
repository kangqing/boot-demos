package com.yunqing.demoatest.algorithm.binarysearch;

import java.util.Arrays;

/**
 * LeetCode410 分割数组的最大值
 * @author yunqing
 * @since 2020/8/22 21:52
 */
public class LeetCode410 {
    public static void main(String[] args) {
        int[] arr = new int[]{7,2,5,10,8};
        int m = 2;
        Solution410 solution410 = new Solution410();
        System.out.println(solution410.splitArray(arr, m));
    }
}

class Solution410 {
    public int splitArray(int[] nums, int m) {
        // 二分查找
        //首先分析出二分查找的边界是数组全部分组，max(nums)是最小的最大值，全部不分组，sum(nums)是最大的最大值
        int low = Arrays.stream(nums).max().getAsInt();
        int high = Arrays.stream(nums).sum();
        while (low < high) {
            int mid = (low + high) / 2;
            // 以这个mid为最大的最大值去分割数组，看能分几组
            int count = this.group(nums, mid);
            if (count > m) {
                //证明分组分多了， mid小了则分组多
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    // 理解， mid越大分组越少
    private int group(int[] nums, int mid) {
        int count = 1; // 最少分一组
        int sum = 0; // 定义每一组的初始值和为0
        for (int x : nums) {
            if (sum + x > mid) {
                // 超过了mid最大数组和，则需要数量加一，sum置为当前数字
                count++;
                sum = x;
            } else {
                sum += x;
            }
        }
        return count;
    }
}
