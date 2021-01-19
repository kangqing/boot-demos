package com.yunqing.demoleetcode.time;

import java.util.Arrays;

/**
 * 12.28 动态规划，股票的买卖
 *
 * @author kangqing
 * @since 2020/12/28 10:12
 */
public class Time12m28 {
    public static void main(String[] args) {

    }
}

/**
 * 动态规划
 */
class Solution12m28 {
    public int maxProfit(int k, int[] prices) {
        if (prices.length == 0) {
            return 0;
        }
        // 由于 n 天 最多买卖股票 n / 2 次
        int n = prices.length;
        k = Math.min(k, n / 2);
        // 用buy[i][j] 表示对于数组prices[0..i] 中的价格而言，进行恰好 j 笔交易，并且当前手上持有一支股票，这种情况下的最大利润；
        // 用sell[i][j] 表示恰好进行 j 笔交易，并且当前手上不持有股票，这种情况下的最大利润。
        int[][] buy = new int[n][k + 1];
        int[][] sell = new int[n][k + 1];
        // 第 0 天 买入股票，减去当天价格
        buy[0][0] = -prices[0];
        // 第 0 天 不买不卖
        sell[0][0] = 0;
        /**
         * 对于 buy[0][0..k]，由于只有 prices[0] 唯一的股价，因此我们不可能进行过任何交易，那么我们可以将所有的buy[0][1..k] 设置为
         * 一个非常小的值，表示不合法的状态。而对于buy[0][0]，它的值为 −prices[0]，即「我们在第 00 天以 prices[0] 的价格买入股票」
         * 是唯一满足手上持有股票的方法。
         *
         * 对于 sell[0][0..k]，同理我们可以将所有的 sell[0][1..k] 设置为一个非常小的值，表示不合法的状态。
         */
        for (int i = 1; i <= k; ++i) {
            buy[0][i] = sell[0][i] = Integer.MIN_VALUE / 2;
        }

        for (int i = 1; i < n; ++i) {
            buy[i][0] = Math.max(buy[i - 1][0], sell[i - 1][0] - prices[i]);
            for (int j = 1; j <= k; ++j) {
                buy[i][j] = Math.max(buy[i - 1][j], sell[i - 1][j] - prices[i]);
                sell[i][j] = Math.max(sell[i - 1][j], buy[i - 1][j - 1] + prices[i]);
            }
        }

        return Arrays.stream(sell[n - 1]).max().getAsInt();
    }
}
