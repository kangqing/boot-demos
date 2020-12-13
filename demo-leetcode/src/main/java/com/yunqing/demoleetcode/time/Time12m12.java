package com.yunqing.demoleetcode.time;

/**
 * 12.12动态规划
 * @author yunqing
 * @since 2020/12/12 18:21
 */
public class Time12m12 {
    public static void main(String[] args) {

    }
}

class Solution12m12{
    public int wiggleMaxLength(int[] nums) {
        int n = nums.length;
        if (n < 2) {
            return n;
        }
        int up = 1;
        int down = 1;
        for (int i = 1; i < n; i++) {
            if (nums[i] > nums[i - 1]) {
                up = Math.max(up, down + 1);
            } else if (nums[i] < nums[i - 1]) {
                down = Math.max(up + 1, down);
            }
        }
        return Math.max(up, down);
    }
}