package com.yunqing.demoleetcode.algorithm.dp;

/**
 * LeetCode1770 执行乘法运算的最大分数
 * @author kangqing
 * @since 2021/2/22 17:51
 */
public class LeetCode1770 {
    public static void main(String[] args) {

    }
}

class Solution {
    public int maximumScore(int[] nums, int[] multipliers) {
        int n = nums.length;
        int m = multipliers.length;
        /**
         * dp[i][j]  代表 nums 前面取 i 个数， 后面取 j 个数
         */
        int[][] dp = new int[m + 1][m + 1];
        int res =  Integer.MIN_VALUE;
        // k = i + j 代表总数
        for (int k = 1; k <= m; k++) {
            for (int i = 0; i <= k; i++) {
                if (i == 0) {
                    // 全部从nums尾部取的值
                    dp[0][k] = dp[0][k - 1] + nums[n - k] * multipliers[k - 1];
                } else if (i == k) {
                    // 全部从nums 头部取的值
                    dp[i][0] = dp[i - 1][0] +  nums[i - 1] * multipliers[k - 1];
                } else {
                    dp[i][k - i] = Math.max(dp[i][k - i - 1] + nums[n - k + i] * multipliers[k - 1],
                            dp[i - 1][k - i] + nums[i - 1] * multipliers[k - 1]);
                }
                if (k == m) res = Math.max(res, dp[i][k - i]);
            }
        }

        return res;

    }
}
