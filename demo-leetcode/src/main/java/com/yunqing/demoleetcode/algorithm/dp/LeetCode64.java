package com.yunqing.demoleetcode.algorithm.dp;

/**
 * @author yx
 * @since 2020/12/23 17:49
 */
public class LeetCode64 {
    public static void main(String[] args) {

    }
}

/**
 * 动态规划
 */
class Solution64 {
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j > 0) dp[i][j] = grid[i][j] + dp[i][j - 1];
                else if (i > 0 && j == 0) dp[i][j] = grid[i][j] + dp[i - 1][j];
                else if (i > 0) dp[i][j] = Math.min(grid[i][j] + dp[i][j - 1], grid[i][j] + dp[i - 1][j]);
            }
        }
    }
}