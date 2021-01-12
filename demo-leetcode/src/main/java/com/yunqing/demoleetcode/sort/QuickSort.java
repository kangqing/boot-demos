package com.yunqing.demoleetcode.sort;

import java.util.Arrays;

/**
 * 快速排序  不稳定：即会破坏相同元素的原有顺序
 *
 * 时间复杂度 O(n log n)
 *
 * 原理：以索引 0 位置为参照位，定义双指针 left = 0， right = arr.length - 1
 * left向右 找到大于参照位的索引， right 向左找到小于参照位的索引， 索引位置的值交换，参照位和中间值进行交换
 * 这样就形成参照位 左边全是小于参照位的值， 右边全是大于参照位的值
 * 然后递归参照位的左面和右面
 * @author kangqing
 * @since 2021/1/12 14:42
 */
public class QuickSort {
    public static void main(String[] args) {
        // 定义需要进行排序的数组
        int[] arr = {5, 7, 1, 13, 6, 2};
        // 下面进行快速排序

        quickSort(arr, 0, arr.length - 1);

    }

    private static void quickSort(int[] arr, int beginIndex, int endIndex) {
        if (beginIndex >= endIndex) {
            return;
        }
        // 定义双指针，和参照位key（我以第一位为参照位）
        int left = beginIndex;
        int right = endIndex;
        int key = arr[left];

        // 双指针各自往中间走，left找到 > key的位置， right找到 < key的位置， 交换
        while (left < right) {
            // 找到 arr[right] < key
            while (arr[right] >= key && left < right)
                right--;
            // 因为arr[left] 的参照元素已经存在 key中，正好可以利用此位置交换
            arr[left] = arr[right];

            // 找到 arr[left] > key
            while (arr[left] <= key && left < right)
                left++;
            // 交换
            arr[right] = arr[left];
        }
        // 基准值归位，arr[left] 已经在首位了，所以不用操作了，只需将原来的首位 Key 放到left索引处即可
        arr[left] = key;
        System.out.println("参照基准值" + key + "排序之后：" + Arrays.toString(arr));

        // 递归快排基准值之前的子序列
        quickSort(arr, beginIndex, left - 1);
        // 递归快排基准值之后的子序列
        quickSort(arr, left + 1, endIndex);
    }
}
