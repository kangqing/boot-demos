package com.yunqing.demoatest.algorithm.dynamicmemory;

/**
 * @Description 不同的二叉搜索树
 * @Author yunqing
 * @Data 2020/7/15 22:06
 */
public class LeetCode96 {
    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.numTrees(5));
    }
}

class Solution {
    public int numTrees(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        if (n == 0) {
            return 0;
        }
        // 长度为 1 到 n 的整数
        for (int len = 1; len <= n; len++) {
            // 从1开始将不同的数字作为根节点，只需要考虑到当前len 的长度
            for (int root = 1; root <= len; root++) {
                int left = root - 1; // 左子树的长度
                int right = len - root; // 右子树的长度
                dp[len] += dp[left] * dp[right];
            }
        }
        return dp[n];
    }
}
