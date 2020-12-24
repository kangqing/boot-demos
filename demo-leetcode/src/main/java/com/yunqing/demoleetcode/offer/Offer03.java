package com.yunqing.demoleetcode.offer;

import java.util.Arrays;

/**
 * 剑指Offer 03 数组中重复的数字
 * @author yx
 * @since 2020/12/24 11:13
 */
public class Offer03 {
    public static void main(String[] args) {

    }
}

class SolutionOffer03 {
    public int findRepeatNumber(int[] nums) {
        Arrays.sort(nums);
        for(int i = 1; i < nums.length; i++) {
            if(nums[i] == nums[i - 1]) return nums[i];
        }
        return -1;
    }
}
