package com.yunqing.demoleetcode.algorithm.dp;

/**
 * LeetCode64 最小路径和 动态规划
 * @author kangqing
 * @since 2020/12/23 17:49
 */
public class LeetCode64 {
    public static void main(String[] args) {
        final Solution64 s = new Solution64();
        System.out.println(s.minPathSum(new int[][]{{1,2,3},{4,5,6}}));
    }
}

/**
 * 动态规划
 */
class Solution64 {
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        // 构造一个二维数组用来代表图中某个位置的最小路径和
        int[][] dp = new int[m][n];
        // 所以 [0][0] 位置的最小路径和是 grid[0][0]
        dp[0][0] = grid[0][0];
        // 循环二维数组，求出每一个位置的最小路径和
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 推导出动态规划的转换方程，由于当前位置只能由 上面 或者 左面 而来，所以可得出以下三个转换方程
                if (i == 0 && j > 0) dp[i][j] = grid[i][j] + dp[i][j - 1];
                else if (i > 0 && j == 0) dp[i][j] = grid[i][j] + dp[i - 1][j];
                else if (i > 0) dp[i][j] = Math.min(grid[i][j] + dp[i][j - 1], grid[i][j] + dp[i - 1][j]);
            }
        }
        // 即走到右下角格子的最小路径和
        return dp[m - 1][n - 1];
    }
}