package com.yunqing.demoleetcode.algorithm.dp;

/**
 * LeetCode1690 石子游戏 VII
 * @author yx
 * @since 2020/12/22 11:35
 */
public class LeetCode1690 {
    public static void main(String[] args) {
        int[] arr = {5,3,1,4,2};
        final Solution1690 s = new Solution1690();
        System.out.println(s.stoneGameVII(arr));

    }
}

/**
 * 动态规划
 * 动态转移方程：
 */
class Solution1690 {
    public int stoneGameVII(int[] stones) {
        int len = stones.length;
        // 记录区间和, int[i][j]代表索引i到j的区间和
        int[][] sum = new int[len + 1][len + 1];
        for (int i = 0; i < len; i++) {
            for (int j = i; j < len; j++) {
                if (i == j) sum[i][j] = stones[i];
                else sum[i][j] = stones[j] + sum[i][j - 1];
            }
        }
        // 表示剩下的石头堆为 i 到 j 时，当前玩家与另一个玩家得分差的最大值
        // 状态转移方程
        /**
         * sum[i][j]：表示从 i 到 j 的石头价值总和
         * dp[i][j]：表示剩下的石头堆为 i 到 j 时，当前玩家与另一个玩家得分差的最大值，当前玩家不一定是先手Alice
         *
        1.最初始的时候：i == j ，dp[i][j] = 0，因为删了之后没得取
        2.当 j - i == 1 时，dp[i][j] = max(stones[i], stones[j])，因为我要利益最大化，我肯定删掉一个较小的石头，取最大得分，反正下一个人是没分的
        3.当 j - i > 1 时， dp[i][j] = max(sum[i + 1][j] - dp[i + 1][j], sum[i][j - 1] - dp[i][j - 1])
        本次删除，A能从左端删或者从右端删，
        从左端删，在剩下的石头中（不考虑前面的），B将比A得分多dp[i + 1][j]
        从右端删，在剩下的石头中（不考虑前面的），B将比A得分多dp[i][j - 1]
         **/
        int[][] dp = new int[len + 1][len + 1];
        for (int i = len - 1; i >= 0; i--) {
            for (int j = i + 1; j < len; j++) {
                if (j - i == 1) dp[i][j] = Math.max(stones[i], stones[j]);
                else dp[i][j] = Math.max(sum[i + 1][j] - dp[i + 1][j], sum[i][j - 1] - dp[i][j - 1]);
            }
        }

        return dp[0][len - 1];
    }
}
