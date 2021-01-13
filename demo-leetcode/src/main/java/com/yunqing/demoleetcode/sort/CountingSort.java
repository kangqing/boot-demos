package com.yunqing.demoleetcode.sort;

import java.util.Arrays;

/**
 * 计数排序 稳定排序：不会改变原有相同元素前后位置
 *
 * 时间复杂度O(n + k)
 *
 * @author kangqing
 * @since 2021/1/13 10:21
 */
public class CountingSort {
    public static void main(String[] args) {
        // 定义需要进行排序的数组
        int[] arr = {5, 7, 1, 13, 6, 2};
        // 下面进行计数排序

        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        //找出数组中的最大最小值
        for (int j : arr) {
            max = Math.max(max, j);
            min = Math.min(min, j);
        }

        int[] help = new int[max - min + 1];

        //找出每个数字出现的次数
        for (int j : arr) {
            int mapPos = j - min;
            help[mapPos]++;
        }

        //计算每个数字应该在排序后数组中应该处于的位置
        for (int i = 1; i < help.length; i++) {
            help[i] = help[i - 1] + help[i];
        }

        //根据help数组进行排序
        int[] res = new int[arr.length];
        for (int j : arr) {
            int post = --help[j - min];
            res[post] = j;
        }

        System.out.println(Arrays.toString(res));
    }
}
