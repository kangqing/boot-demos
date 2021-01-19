package com.yunqing.demoatest.algorithm.array;

/**
 * @author kangqing
 * @description LeetCode283 移动零
 * @date 2020/8/12 15:48
 */
public class LeetCode283 {
    public static void main(String[] args) {
        var arr = new int[]{0, 1, 3, 0, 0, 3, 5, 9};
        var s = new Solution283();
        s.moveZeroes(arr);
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }
}

class Solution283 {
    public void moveZeroes(int[] nums) {
        var fast = 0;
        var slow = 0;
        while(fast < nums.length) {
            if (nums[fast++] != 0) {
                nums[slow++] = nums[fast - 1];
            }
        }

        for (int i = slow; i < nums.length; i++) {
            nums[i] = 0;
        }

    }
}
