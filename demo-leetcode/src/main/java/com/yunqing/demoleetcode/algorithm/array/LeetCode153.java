package com.yunqing.demoleetcode.algorithm.array;

/**
 * @author yx
 * @description LeetCode153 寻找旋转排序数组中的最小值
 * @date 2020/8/12 14:13
 */
public class LeetCode153 {
    public static void main(String[] args) {
        Solution153 solution153 = new Solution153();
        System.out.println(solution153.findMin(new int[]{4,5,6,1,2,3}));
    }
}

class Solution153 {
    public int findMin(int[] nums) {
        var head = 0;
        var tail = nums.length - 1;
        if(nums[head] <= nums[tail]) {
            return nums[head];
        }else{
            while(tail > 0) {
                if(nums[tail - 1] > nums[tail]) {
                    return nums[tail];
                }
                tail--;
            }
        }
        return -1;
    }
}