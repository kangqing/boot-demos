package com.yunqing.demoleetcode.sort;

import java.util.Arrays;

/**
 * 选择排序 不稳定：即会破坏相同元素的原有顺序
 *
 * 时间复杂度 O(n²)
 *
 * 简单选择排序的基本思想：
 * 给定数组：int[] arr = {里面n个数据}；
 * 第1趟排序，在待排序数据 arr[1]~arr[n] 中选出最小的数据，将它与 arr[1] 交换；
 * 第2趟，在待排序数据 arr[2]~arr[n] 中选出最小的数据，将它与 r[2] 交换；
 * 以此类推，第i趟在待排序数据 arr[i]~arr[n] 中选出最小的数据，将它与 r[i] 交换，直到全部排序完成。
 * @author kangqing
 * @since 2021/1/12 10:11
 */
public class SelectionSort {
    public static void main(String[] args) {
        // 定义需要进行排序的数组
        int[] arr = {5, 7, 1, 13, 6, 2};
        // 下面进行选择排序

        // 进行第 i 趟排序
        for (int i = 0; i < arr.length - 1; i++) {
            int k = i;
            // 获取除去已经排好序的数之外的最小的数的索引
            for (int j = k + 1; j < arr.length; j++) {
                if (arr[j] < arr[k]) k = j;
            }
            // 如果 k 的索引不是 i 则把最小的数与 i 位置进行交换（即把最小的数放到 i 位置）
            if (k != i) {
                int temp = arr[i];
                arr[i] = arr[k];
                arr[k] = temp;
            }
            System.out.println("第" + i + "趟排序结果 ：" + Arrays.toString(arr));
        }

        System.out.println(Arrays.toString(arr));
    }
}
