package com.yunqing.demoleetcode.algorithm.array;

import java.util.Arrays;

/**
 * LeetCode31 下一个排列
 * @author yunqing
 * @since 2020/11/10 21:11
 */
public class LeetCode31 {
    public static void main(String[] args) {
        int[] a = new int[] {1, 2, 3};
        Solution31 solution31 = new Solution31();
        solution31.nextPermutation(a);
        System.out.println(Arrays.toString(a));
    }
}

class Solution31 {
    public void nextPermutation(int[] nums) {
        int i = nums.length - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }
        if (i >= 0) {
            // 存在前置位小于后置位的
            // 6 5 3 7 6 5 3
            // 6 5 5 3 3 6 7
            int j = nums.length - 1;
            while (j >= 0 && nums[i] >= nums[j]) {
                // 找到大于nums[i] 的最接近的数
                j--;
            }
            // 交换
            swap(nums, i, j);
        }
        // 否则直接翻转所有数字，变成最小的 此时i = -1
        reverse(nums, i + 1);

    }
    // 互换两个位置
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    /**
     * 翻转从指定索引开始
     */
    private void reverse(int[] nums, int index) {
        int left = index;
        int right = nums.length - 1;
        while (left < right) {
            // 只要是左指针索引 < 右指针索引，就一直交换
            swap(nums, left, right);
            left++;
            right--;
        }
    }
}
