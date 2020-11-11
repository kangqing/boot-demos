package com.yunqing.demoleetcode.algorithm.array;

/**
 * LeetCode122 买卖股票的最佳时机 II
 * @author yunqing
 * @since 2020/11/11 19:55
 */
public class LeetCode122 {
}

class Solution122 {
    public int maxProfit(int[] prices) {
        int res = 0;
        int n = prices.length;
        for (int i = 1; i < n; i++) {
            res += Math.max(0, prices[i] - prices[i - 1]);
        }
        return res;
    }
}
