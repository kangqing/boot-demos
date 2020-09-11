package com.yunqing.demoleetcode.algorithm.array;


/**
 * LeetCode485 最大连续1的个数
 * @author yunqing
 * @since 2020/8/8 22:23
 */
public class LeetCode485 {
    public static void main(String[] args) {
        Solution485 solution485 = new Solution485();
        int[] arr = new int[]{1};
        System.out.println(solution485.findMaxConsecutiveOnes(arr));
    }
}

/**
 * 双指针
 */
class Solution485 {
    public int findMaxConsecutiveOnes(int[] nums) {
        int max = 0;
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                count++;
            }
            if (nums[i] == 0 || i == nums.length -1) {
                max = Math.max(count, max);
                count = 0;
            }
        }
        return max;
    }
}
