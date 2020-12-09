package com.yunqing.demoleetcode.time;

import java.util.Arrays;

/**
 * 12.9 动态规划
 * @author yx
 * @since 2020/12/9 10:25
 */
public class Time12m9 {
    public static void main(String[] args) {
        Solution12m9 s = new Solution12m9();
        System.out.println(s.uniquePaths(3, 7));
    }
}

class Solution12m9 {
    public int uniquePaths(int m, int n) {
        // 构造一个二维数组,模拟机器人路径
        int[][] f = new int[m][n];
        // 由于 m= 0 或者n = 0都只有一种路径
        for(int i = 0; i < m; i++) f[i][0] = 1;
        for(int j = 0; j < n; j++) f[0][j] = 1;

        // 求每个格子的机器人路径数
        for(int i = 1; i < m; i++) {
            for(int j = 1; j < n; j++) {
                // 由于只能从上面或者左面走过来，所以可得动态转移方程
                f[i][j] = f[i - 1][j] + f[i][j - 1];
            }
        }
        System.out.println(Arrays.deepToString(f));
        // 第三行第七列，由于从0开始
        return f[m - 1][n - 1];
    }
}
