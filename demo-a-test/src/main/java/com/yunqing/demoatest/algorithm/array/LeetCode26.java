package com.yunqing.demoatest.algorithm.array;

/**
 * @author kangqing
 * @description LeetCode26 删除排序数组中的重复项
 * @date 2020/8/12 14:44
 */
public class LeetCode26 {
    public static void main(String[] args) {
        var solution26 = new Solution26();
        var nums = new int[]{1, 1, 2, 2, 3, 3, 3, 3, 4};
        System.out.println(solution26.removeDuplicates(nums));
    }
}

class Solution26 {
    public int removeDuplicates(int[] nums) {
        var fast = 0;
        var slow = 0;
        while (fast < nums.length - 1) {
            if (nums[slow] != nums[++fast]) {
                nums[++slow] = nums[fast];
            }
        }
        return slow + 1;
    }
}
