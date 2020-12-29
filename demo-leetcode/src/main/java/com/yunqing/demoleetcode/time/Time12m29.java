package com.yunqing.demoleetcode.time;

/**
 * 12.29 贪心
 * @author yx
 * @since 2020/12/29 11:13
 */
public class Time12m29 {
    public static void main(String[] args) {

    }
}

class Solution12m29 {
    public int minPatches(int[] nums, int n) {
        int patches = 0;
        long x = 1;
        int length = nums.length, index = 0;
        while (x <= n) {
            if (index < length && nums[index] <= x) {
                x += nums[index];
                index++;
            } else {
                x *= 2;
                patches++;
            }
        }
        return patches;
    }
}
