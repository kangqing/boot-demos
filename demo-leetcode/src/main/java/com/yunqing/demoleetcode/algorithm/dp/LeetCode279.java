package com.yunqing.demoleetcode.algorithm.dp;

import java.util.*;

/**
 * 堆栈溢出自己写的
 * LeetCode279 完全平方数
 * @author yx
 * @since 2020/9/14 15:40
 */
public class LeetCode279 {
    public static void main(String[] args) {
        Solution279 solution279 = new Solution279();
        System.out.println(solution279.numSquares(7));
    }
}

/**
 * 自己写的递归回溯，会造成堆栈溢出，这题明显是动态规划
 * 动态规划
 * 求组成目标数字的最小数量
 * [1, 4] 组成目标的值
 * target = 7
 * f(7) = f(7 - 1) + 1次
 * f(7) = f(7 - 4) + 1次
 * f(7) = Math.min(f(7 - 1) + 1, f(7 - 4) + 1)
 * 以此类推
 * f(0) = 0次是已知
 */
class Solution279 {
    public int numSquares(int n) {
        List<Integer> list = new ArrayList<>();
        int max_index = (int) Math.sqrt(n) + 1;
        for (int i = 0; i < max_index; i++) {
            list.add(i * i);
        }

        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int j = 1; j <= n; j++) {
            //循环计算每一个元素的最小组成数
            for (int k = 1; k < max_index; k++) {
                //循环减去每一个平方数
                if (j < list.get(k))
                    break;
                dp[j] = Math.min(dp[j], dp[j - list.get(k)] + 1);
            }
        }
        return dp[n];

    }
}
