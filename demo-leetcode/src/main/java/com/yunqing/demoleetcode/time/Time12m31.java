package com.yunqing.demoleetcode.time;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 12.31贪心
 *
 * @author kangqing
 * @since 2020/12/31 9:32
 */
public class Time12m31 {
    public static void main(String[] args) {
        final Solution12m31 s = new Solution12m31();
        System.out.println(s.eraseOverlapIntervals(new int[][] {{1, 2}, {2, 3}}));

    }
}

class Solution12m31 {
    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length == 0) return 0;
        // 根据右边界排序
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[1]));
        // 二维数组长度
        int len = intervals.length;
        // 第一个数组的右边界是
        int right = intervals[0][1];
        // 不重叠的为 1 个
        int res = 1;
        // 循环剩下的数组，如果不重叠 + 1
        for (int i = 1; i < len; i++) {
            if (intervals[i][0] >= right) {
                // 不重叠 + 1
                res++;
                // 右边界更改
                right = intervals[i][1];
            }
        }
        return len - res;

    }
}
