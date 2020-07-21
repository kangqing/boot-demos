package com.yunqing.demoatest.algorithm.array;

/**
 * @Description 搜索插入位置
 * @Author yunqing
 * @Data 2020/7/21 15:57
 */
public class LeetCode35 {
    public static void main(String[] args) {
        int[] arr = new int[]{1,3,5,6};
        Solution35 s = new Solution35();
        System.out.println(s.searchInsert(arr, 0));
    }
}

class Solution35 {
    public int searchInsert(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < target) {
                continue;
            }
            return i;
        }
        return nums.length;
    }
}


