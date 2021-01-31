package com.yunqing.demoleetcode.topics2020;

/**
 * 买卖股票的最佳时机，只能买卖一次
 */
public class No3BuySellShares {
    public static void main(String[] args) {
        int[] arr = {7,1,5,3,6,4};
        SolutionNo3 solutionNo3 = new SolutionNo3();
        System.out.println(solutionNo3.maxProfit(arr));
    }
}

class SolutionNo3 {
    public int maxProfit(int[] prices) {
        // 股票最低价格
        int min = Integer.MAX_VALUE;
        // max最大收益
        int max = 0;
        for (int price : prices) {
            if (price < min) {
                min = price;
            } else if (price - min > max) {
                max = price - min;
            }
        }
        return max;
    }
}
