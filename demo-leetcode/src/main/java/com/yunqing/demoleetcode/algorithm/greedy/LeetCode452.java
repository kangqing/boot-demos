package com.yunqing.demoleetcode.algorithm.greedy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * LeetCode452 用最少数量的箭引爆气球
 * @author yunqing
 * @since 2020/11/23 20:41
 */
public class LeetCode452 {
    public static void main(String[] args) {
        int[][] a = {{0, 9}, {0, 6}, {2, 9}, {2, 8}, {3, 9}, {3, 8}, {3, 9}, {6, 8}, {7, 12}, {9, 10}};
        Solution452 solution452 = new Solution452();
        int minArrowShots = solution452.findMinArrowShots(a);
        System.out.println(minArrowShots);
    }
}

class Solution452 {
    public int findMinArrowShots(int[][] points) {
        if (points.length == 0) return 0;
        // 按照数组的第二个元素排序
        int[][] ints = Arrays.stream(points)
                .sorted(Comparator.comparingInt(o -> o[1]))
                .toArray(int[][]::new);
        System.out.println(Arrays.deepToString(ints));
        int currIndex = 0;
        int res = 0;
        for (int i = 0; i < ints.length; i++) {
            int[] anInt = ints[currIndex];
            if (anInt[1] < ints[i][0]) {
                res++;
                currIndex = i--;
            }
        }
        return ++res;
    }
}
