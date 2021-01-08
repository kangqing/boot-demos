package com.yunqing.demoleetcode.time.time202101;


import java.util.Arrays;

/**
 * 1.8 数组翻转
 * @author kangqing
 * @since 2021/1/8 21:14
 */
public class Time1m8 {
    public static void main(String[] args) {
        final Solution1m8 s = new Solution1m8();
        int[] arr = new int[]{1};
        s.rotate(arr, 2);
        System.out.println(Arrays.toString(arr));
    }
}
class Solution1m8 {
    public void rotate(int[] nums, int k) {
        int len = nums.length;
        // 取余数，防止 k >= 数组的长度
        k %= len;
        reverse(nums, 0, len - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, len - 1);
    }

    /**
     * 双指针翻转数组
     * @param nums
     * @param left
     * @param right
     */
    private void reverse(int[] nums, int left, int right) {
        while (left < right) {
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left++;
            right--;
        }
    }
}