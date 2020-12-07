package com.yunqing.demoleetcode.time;

import java.util.Arrays;

/**
 * 12月2， 考点：单调栈
 * @author yx
 * @since 2020/12/2 15:23
 */
public class Time12m2 {
    public static void main(String[] args) {
        int[] a = {3, 4, 6, 5};
        int[] b = {9, 1, 2, 5, 8, 3};
        Solution12m2 solution12m2 = new Solution12m2();
        System.out.println(Arrays.toString(solution12m2.maxNumber(a, b, 5)));
    }
}
class Solution12m2 {
    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        // 分析从nums1中选择 x 个数， 则需要从nums2中选择 k - x个数
        int[] res = new int[k];
        int m = nums1.length;
        int n = nums2.length;
        for (int i = 0; i <= k && i <= m; i++) {
            if (k - i >= 0 && k - i <= n) {
                // 合并两个子序列
                // 合并
                int[] tmp = merge(subMaxNumber(nums1, i), subMaxNumber(nums2, k - i));
                // 取最大值
                if (compare(tmp, 0, res, 0)) {
                    res = tmp;
                }
            }
        }
        return res;
    }

    // 类似于单调递减栈
    private int[] subMaxNumber(int[] nums, int len) {
        int[] subNums = new int[len];
        int cur = 0, rem = nums.length - len; // rem 表示还可以删去多少字符
        for (int i = 0; i < nums.length; i++) {
            while (cur > 0 && subNums[cur - 1] < nums[i] && rem > 0) {
                cur--;
                rem--;
            }
            if (cur < len) {
                subNums[cur++] = nums[i];
            } else {
                rem--; // 避免超过边界而少删字符
            }
        }
        return subNums;
    }

    public int[] merge(int[] nums1, int[] nums2) {
        int[] res = new int[nums1.length + nums2.length];
        int cur = 0, p1 = 0, p2 = 0;
        while (cur < nums1.length + nums2.length) {
            if (compare(nums1, p1, nums2, p2)) { // 不能只比较当前值，如果当前值相等还需要比较后续哪个大
                res[cur++] = nums1[p1++];
            } else {
                res[cur++] = nums2[p2++];
            }
        }
        return res;
    }

    public boolean compare(int[] nums1, int p1, int[] nums2, int p2) {
        if (p2 >= nums2.length) return true;
        if (p1 >= nums1.length) return false;
        if (nums1[p1] > nums2[p2]) return true;
        if (nums1[p1] < nums2[p2]) return false;
        return compare(nums1, p1 + 1, nums2, p2 + 1);
    }
}
