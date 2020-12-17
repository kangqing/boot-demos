package com.yunqing.demoleetcode.time;

/**
 * 12.17 动态规划，先找出转移方程，股票的最佳买卖时机利益最大化
 * @author yx
 * @since 2020/12/17 9:24
 */
public class Time12m17 {
    public static void main(String[] args) {
        Solution12m17 s = new Solution12m17();
        System.out.println(s.maxProfit1(new int[]{1, 3, 2, 8, 4, 9}, 2));
    }
}

class Solution12m17 {
    public int maxProfit(int[] prices, int fee) {
        int n = prices.length;
        // 构造一个二维数组，完成动态转移方程
        // 当前天最佳手里没股票dp[i][0]代表， 手里有股票dp[i][1]代表
        // dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i] - fee) // 解释：当天最佳可能是昨天手里没股票最佳，或者昨天手里有股票 + 今天卖出的收益 - 手续费
        // dp[i][1] = Math.max(dp[i][1], dp[i][0] - prices[i]) // 解释： 当天最佳手里有股票可能是昨天手里有股票最佳，或者昨天手里没股票，买入了今天的股票
        int[][] dp = new int[n][2];
        // 第一天手里无股票
        dp[0][0] = 0;
        // 第一天手里有股票
        dp[0][1] = -prices[0];
        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i] - fee);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }
        // 由于全部交易结束后，持有股票的收益一定低于不持有股票的收益，因此这时候dp[n−1][0] 的收益必然是大于dp[n−1][1] 的，最后的答案即为dp[n−1][0]
        return dp[n - 1][0];
    }

    public int maxProfit1(int[] prices, int fee) {
        // 注意到字状态转移方程中，dp[i][0] 和 dp[i][1] 只会从dp[i−1][0] 和dp[i−1][1] 转移而来，
        // 因此我们不必使用数组存储所有的状态，而是使用两个变量sell 以及 buy 分别表示 dp[..][0] 和dp[..][1] 直接进行状态转移即可
        int n = prices.length;
        int sell = 0;
        int buy = -prices[0];
        for (int i = 1; i < n; i++) {
            sell = Math.max(sell, buy + prices[i] - fee);
            buy = Math.max(buy, sell - prices[i]);
        }

        return sell;
    }
}