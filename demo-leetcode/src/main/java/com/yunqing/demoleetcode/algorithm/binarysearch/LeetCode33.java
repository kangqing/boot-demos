package com.yunqing.demoleetcode.algorithm.binarysearch;

/**
 * LeetCode33 搜索旋转排序数组
 * @author yx
 * @date 2020/8/14 14:02
 */
public class LeetCode33 {
    public static void main(String[] args) {
        Solution33 solution33 = new Solution33();
        var arr = new int[]{1, 3};
        var target = 3;
        System.out.println(solution33.search(arr, target));
    }
}

class Solution33 {
    public int search(int[] nums, int target) {
        if (nums == null) {
            return -1;
        }
        var len = nums.length - 1;
        var left = 0;
        var right = len;
        while (left <= right) {
            var mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            }else if (nums[0] <= nums[mid]) {
                //左边是有序的
                if (nums[0] <= target && target < nums[mid]) right = --mid;
                else left = ++mid;
            }else {
                //右边是有序的
                if (target > nums[mid] && target <= nums[len]) left = ++mid;
                else right = --mid;
            }
        }
        return -1;
    }
}