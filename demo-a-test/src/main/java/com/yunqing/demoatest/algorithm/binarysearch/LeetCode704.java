package com.yunqing.demoatest.algorithm.binarysearch;

/**
 * @author kangqing
 * @description LeetCode704 二分查找
 * @date 2020/8/13 14:47
 */
public class LeetCode704 {
    public static void main(String[] args) {
        var s = new Solution704();
        var arr = new int[]{-1,0,3,5,9,12};
        System.out.println(s.search(arr, 9));
    }
}

class Solution704 {
    int search(int[] nums, int target) {
        var left = 0;
        var right = nums.length - 1;
        while(left <= right) {
            var center = left + (right - left) / 2;
            if(nums[center] == target) return center;
            else if(nums[center] < target) left = ++center;
            else right = --center;
        }
        return -1;
    }
}
