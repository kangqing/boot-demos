package com.yunqing.demoleetcode.sort;

import java.util.Arrays;

import static cn.hutool.core.util.ArrayUtil.swap;

/**
 * 堆排序 不稳定排序：可能会改变相同元素原有的前后位置
 *
 * 时间复杂度 O(n log n)
 *
 * 堆排序的基本思想是：将待排序序列构造成一个大顶堆，此时，整个序列的最大值就是堆顶的根节点。
 * 将其与末尾元素进行交换，此时末尾就为最大值。然后将剩余n-1个元素重新构造成一个堆，这样会得到n个元素的次小值。
 * 如此反复执行，便能得到一个有序序列了
 *
 *  参考：https://www.cnblogs.com/chengxiao/p/6129630.html
 *
 * @author kangqing
 * @since 2021/1/13 9:54
 */
public class HeapSort {

    public static void main(String[] args) {
        // 定义需要进行排序的数组
        int[] arr = {5, 7, 1, 13, 6, 2};
        // 下面进行堆排序

        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    private static void sort(int[] arr) {
        // 构建最大堆
        for (int i = arr.length / 2 - 1; i >= 0 ; i--) {
            // 从第一个非叶子结点从下至上，从右至左调整结构
            adjustHeap(arr, i, arr.length);
        }
        
        // 调整堆结构+交换堆顶元素与末尾元素
        for (int i = arr.length - 1; i > 0; i--) {
            // 将堆顶元素与末尾元素进行交换
            swap(arr, 0, i);
            // 重新对堆进行调整
            adjustHeap(arr, 0, i);
        }
    }

    /**
     * 调整大顶堆（仅是调整过程，建立在大顶堆已构建的基础上）
     * @param arr 数组
     * @param i 堆顶索引
     * @param length 未排好序的数组长度
     */
    private static void adjustHeap(int[] arr, int i, int length) {
        // 先取出当前元素 i
        int temp = arr[i];
        // 从 i 结点的左子结点开始，也就是 2i+1 处开始
        for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {
            // 如果左子结点小于右子结点，k 指向右子结点
            if (k + 1 < length && arr[k] < arr[k + 1]) {
                k++;
            }
            // 如果子节点大于父节点，将子节点值赋给父节点（不用进行交换）
            if (arr[k] > temp) {
                arr[i] = arr[k];
                i = k;
            } else {
                break;
            }
        }
        // 将temp值放到最终的位置
        arr[i] = temp;
    }
}
