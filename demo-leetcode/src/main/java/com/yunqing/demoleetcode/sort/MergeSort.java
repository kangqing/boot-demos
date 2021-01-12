package com.yunqing.demoleetcode.sort;

import java.util.Arrays;

/**
 * Arrays.sort() 底层所用的TimSort算法就是归并排序的优化后的算法
 * 归并排序  稳定排序：不会改变相同元素原有顺序
 *
 * 时间复杂度 O(n log n)
 *
 * 归并排序原理：
 * 分治思想，把数组从中间分开，再把子序列各自分开，直到不能再分，然后再把各个子序列进行合并
 * 参考：https://www.cnblogs.com/chengxiao/p/6194356.html
 * @author kangqing
 * @since 2021/1/12 17:07
 */
public class MergeSort {
    public static void main(String[] args) {
        // 定义需要进行排序的数组
        int[] arr = {5, 7, 1, 13, 6, 2};
        // 下面进行归并排序

        // 递归之前先建立一个用来合并分治元素的数组，防止递归过程中频繁开辟内存空间
        int[] temp = new int[arr.length];
        sort(arr, 0, arr.length - 1, temp);
    }

    private static void sort(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            // 取中间索引
            int mid = (left + right) / 2;
            // 左边进行归并排序，使左序列有序
            sort(arr, left, mid, temp);
            // 右边进行归并排序，使右序列有序
            sort(arr, mid + 1, right, temp);
            // 合并两个有序的子序列
            merge(arr, left, mid, right, temp);
        }
    }

    // 合并两个有序的子序列
    private static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        System.out.println("左序列起始指针值" + arr[left] + "----------右序列起始指针值" + arr[mid + 1]);
        // 左序列起始指针
        int i = left;
        // 右序列起始指针
        int j = mid + 1;
        // k 临时数组起始指针
        int k = 0;
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                // 左序列指针元素小，放进temp中
                temp[k++] = arr[i++];
            } else {
                // 右序列指针元素小，放进temp中
                temp[k++] = arr[j++];
            }
        }
        // 如果左序列有剩余元素，全部装进temp中
        while (i <= mid) {
            temp[k++] = arr[i++];
        }

        // 如果右序列有剩余元素，全部装进temp中
        while (j <= right) {
            temp[k++] = arr[j++];
        }

        // 最后将temp中的元素全部拷贝到原数组中‘
        k = 0;
        while (left <= right)
            arr[left++] = temp[k++];

        System.out.println(Arrays.toString(arr));
    }
}
