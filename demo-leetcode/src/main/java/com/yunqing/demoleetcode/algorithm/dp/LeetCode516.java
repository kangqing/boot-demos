package com.yunqing.demoleetcode.algorithm.dp;

/**
 * 最长回文子序列
 * @author kangqing
 * @since 2021/8/12 21:37
 */
public class LeetCode516 {
    public static void main(String[] args) {

    }
}

class Solution516 {
    public int longestPalindromeSubseq(String s) {
        // 先求出字符串长度
        int n = s.length();
        // 分析，如果一个字符串是回文串，那么拿掉两个端点仍然是回文串
        // 故可以得到动态规划的转移方程：
        // 设 dp[i][j] 为 从 i 到 j 之间的最大回文串长度
        // 如果s[i] == s[j] 则 dp[i][j] = dp[i + 1][j - 1] + 2
        // 否则 dp[i][j] = Math.max(dp[i][j - 1], dp[i + 1][j])
        int[][] dp = new int[n][n];
        for (int i = n - 1; i >= 0; i--) {
            // 一个字符也是回文串
            dp[i][i] = 1;
            char si = s.charAt(i);
            for (int j = i + 1; j < n; j++) {
                char sj = s.charAt(j);
                if (si == sj) dp[i][j] = dp[i + 1][j - 1] + 2;
                else dp[i][j] = Math.max(dp[i][j - 1], dp[i + 1][j]);
            }
        }
        return dp[0][n - 1];
    }
}
