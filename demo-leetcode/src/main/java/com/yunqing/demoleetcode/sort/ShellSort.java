package com.yunqing.demoleetcode.sort;

import java.util.Arrays;

/**
 * **希尔排序** 不稳定排序：可能会改变相同值原有顺序
 *
 * 时间复杂度O(n log n)
 * 希尔排序是把记录按下标的一定增量分组，对每组使用直接插入排序算法排序；
 * 随着增量逐渐减少，每组包含的关键词越来越多，当增量减至1时，整个文件恰被分成一组，算法便终止.
 * 之后简单微调便可得结果
 *
 * 参考：https://blog.csdn.net/qq_33366229/article/details/100153507
 * @author kangqing
 * @since 2021/1/12 21:58
 */
public class ShellSort {
    public static void main(String[] args) {
        // 定义需要进行排序的数组
        int[] arr = {5, 7, 1, 13, 6, 2};
        // 下面进行希尔排序

        // 初始步长为 长度的一半 k ,可将数组分为 2k 组
        for (int k = arr.length / 2; k > 0; k /= 2) {
            // 从 k 开始，逐个对所在的组进行直接插入排序
            for (int i = k; i < arr.length; i++) {
                int j = i;
                int temp = arr[j];
                if (arr[j] < arr[j - k]) {
                    while (j - k >= 0 && temp < arr[j - k]) {
                        // 大值放到 j 位置， 然后向左寻找小值 temp 应该放的位置
                        arr[j] = arr[j - k];
                        j -= k;
                    }
                    // 当退出while循环时候， arr[j] 就是 temp 的插入位置
                    arr[j] = temp;
                    System.out.println(Arrays.toString(arr));
                }
            }
        }

    }
}
