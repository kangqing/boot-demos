package com.yunqing.demoatest.algorithm.recursion;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @Description 三角形最小路径和
 * @Author yunqing
 * @Data 2020/7/14 20:27
 */
public class LeetCode120 {
    public static void main(String[] args) {
        List<List<Integer>> list = Arrays.asList(
                Collections.singletonList(2),
                Arrays.asList(3, 4),
                Arrays.asList(6, 5, 7),
                Arrays.asList(4, 1, 8, 3)
        );
        Solution s0 = new Solution();
        System.out.println(s0.minimumTotal(list));

        Solution1 s1 = new Solution1();
        System.out.println(s1.minimumTotal(list));
    }
}

/**
 * 递归但是会有大量的无用计算
 * 思路：从（i, j）也就是顶点 (0, 0)位置向下转化为
 * min((i + 1, j), (i + 1, j + 1)) 求下一行当前列，和下一行下一列作为顶点继续递归，值小的那一个
 */
class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        return recursion(triangle, 0 , 0);
    }

    /**
     * 递归
     * @return
     */
    private int recursion(List<List<Integer>> triangle, int i, int j) {
        if (triangle.size() == i) {
            return 0;
        }
        return Math.min(recursion(triangle, i + 1, j), recursion(triangle, i + 1, j + 1)) + triangle.get(i).get(j);
    }
}

/**
 * 自下向上推
 * 定义一个足够容量的数组
 * 第一遍循环把最后一行数放进数组中，之后更新数组中的数
 * 数组dp[0] = 最后一行的(0, 1)位置小的数加上其父数字
 * 数组dp[1] = 最后一行的(1, 2)位置小的数加上其父数字
 * 数组dp[2] = 最后一行的(2, 3)位置小的数加上其父数字
 * 数组dp[3] = 最后一行的(3, 4)位置小的数加上其父数字
 * 相当于把最后一行小的数加到上一行父数字上，循环迭代，直到最上面一行，为下面所有行的最小路径和加上本身，更新到dp[0]返回
 *
 */
class Solution1 {
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[] dp = new int[n + 1];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                dp[j] = Math.min(dp[j], dp[j + 1]) + triangle.get(i).get(j);
            }
        }
        return dp[0];
    }
}
