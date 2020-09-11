package com.yunqing.demoleetcode.algorithm.binarysearch;

/**
 * LeetCode162 寻找峰值
 * @author yunqing
 * @since 2020/8/17 7:05
 */
public class LeetCode162 {
    public static void main(String[] args) {
        var s = new Solution162();
        System.out.println(s.findPeakElement(new int[]{1, 2, 1, 3, 5, 5, 4}));
    }
}

/**
 * 思路：二分查找
 * 1.任何一个位置的的mid索引处的值 < mid索引处的值， 那么峰值只可能在当前节点右面
 * 2.反之则有可能在当前节点或者当前节点左边
 * 3.知道不满足left < right为止，left节点则为峰值
 */
class Solution162 {
    public int findPeakElement(int[] nums) {
        var left = 0;
        var right = nums.length - 1;
        while (left < right) {
            var mid = (left + right) / 2;
            if (nums[mid] < nums[mid + 1]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
}