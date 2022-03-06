package com.yunqing.demoleetcode.sort;

import java.util.Arrays;

/**
 * 快速排序  不稳定：即会破坏相同元素的原有顺序
 *
 * 时间复杂度 O(n log n)
 *
 * 原理：以索引 end 位置为参照位，定义双指针 left = -1， right = end
 * right 指针向后查找，小于 end 参照位则给 left ++,然后和 right 位置的值交换，最后把 left++ 和 end位置交换
 * 这样就形成参照位 左边全是小于参照位的值， 右边全是大于参照位的值
 * 然后递归参照位的左面和右面
 * @author kangqing
 * @since 2021/1/12 14:42
 */
public class QuickSort {
    public static void main(String[] args) {
        // 定义需要进行排序的数组
        int[] arr = {-2, 7, 1, 13, 6, 2, -1, 10, -2};
        // 下面进行快速排序

        quickSort(arr, 0, arr.length - 1);

    }

    /**
     * 快速排序的基本分治思想
     *
     * @param arr        数组
     * @param beginIndex 开始位置
     * @param endIndex   结束位置
     */
    private static void quickSort(int[] arr, int beginIndex, int endIndex) {
        if (beginIndex < endIndex) {
            int key = partition(arr, beginIndex, endIndex);
            System.out.println("参照基准值" + arr[key] + "排序之后：" + Arrays.toString(arr));
            // 递归快排基准值之前的子序列
            quickSort(arr, beginIndex, key - 1);
            // 递归快排基准值之后的子序列
            quickSort(arr, key + 1, endIndex);
        }

    }

    /**
     * 进行快速排序扫描，核心代码
     *
     * @param arr        数组
     * @param beginIndex 开始位置
     * @param endIndex   结束位置
     * @return 返回参照值
     */
    private static int partition(int[] arr, int beginIndex, int endIndex) {
        // 选择一个值作为参照值，把小于参照值的放在左边，大于参照值的放在右边，我这里选择最后一位作为参照值
        //int key = endIndex;// 标识一下
        // 定义双指针,t1在一个无效位置， t2在第一位
        int t1 = beginIndex - 1;
        // 如果t2找到一个小于 key 的，就把t1 ++ 然后交换位置
        for (int i = beginIndex; i < endIndex; i++) {
            if (arr[i] < arr[endIndex]) {
                t1++;
                swap(arr, i, t1);
            }
        }
        // 结束之后再次 t1 ++ 和 参照位也就是最后一位交换
        t1++;
        swap(arr, t1, endIndex);
        return t1;

    }

    /**
     * 交换位置
     * @param arr 数组
     * @param key 交换的索引1
     * @param endIndex 交换的索引2
     */
    private static void swap(int[] arr, int key, int endIndex) {
        if (key != endIndex) {
            int temp = arr[key];
            arr[key] = arr[endIndex];
            arr[endIndex] = temp;
        }
    }

}
