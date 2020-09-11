package com.yunqing.demoleetcode.algorithm.array;


/**
 * LeetCode27 移除元素
 * @author yunqing
 * @since 2020/8/8 22:01
 */
public class LeetCode27 {
    public static void main(String[] args) {
        int[] nums = new int[]{3, 2, 2, 3};
        Solution27 solution27 = new Solution27();
        System.out.println(solution27.removeElement(nums, 3));
    }
}

/**
 * 双指针解法之快慢指针
 * fast指针每次移动一个位置
 * slow指针当元素不为val时候才移动一个位置
 */
class Solution27 {
    public int removeElement(int[] nums, int val) {
        int fast = 0;
        int slow = 0;
        while (fast < nums.length) {
            if (nums[fast] != val) {
                nums[slow] = nums[fast];
                slow++;
            }
            fast++;
        }
        for (int i = 0; i < slow; i++) {
            System.out.println(nums[i]);
        }
        return slow;
    }
}
