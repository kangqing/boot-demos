package com.yunqing.demoleetcode.algorithm.greedy;

/**
 * LeetCode1664 生成平衡数组的方案数
 * @author yunqing
 * @since 2020/11/23 21:38
 */
public class LeetCode1664 {
    public static void main(String[] args) {
        int[] a = {2, 1, 6, 4};
        Solution1664 solution1664 = new Solution1664();
        System.out.println(solution1664.waysToMakeFair(a));
    }
}

class Solution1664 {
    public int waysToMakeFair(int[] nums) {
        int res = 0;
        int ji = 0;
        int ou = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i % 2 == 0) ou += nums[i];
            else ji += nums[i];
        }

        for (int i = 0; i < nums.length; i++) {
            if (i % 2 == 0) {
                // 如果删除的是偶数
                ou -= nums[i];
                if (ou == ji) res++;
                ji += nums[i]; // 关键步骤：删除偶数，则比较之后加给奇数，反之，一样
            } else {
                ji -= nums[i];
                if (ou == ji) res++;
                ou += nums[i];
            }
        }
        return res;
    }
}
