package com.yunqing.demoleetcode.sort;

import java.util.Arrays;

/**
 * 基数排序， 稳定排序
 *
 * 时间复杂度O(n * k)
 *
 * 原理：假如最大的数是三位数，先按照个位从小到大排序，相同的放进同一个桶，然后取出来，
 * 再按照十位从小到大排序，
 * 然后按照百位从小到大排序，OK完成了
 *
 * 参考： https://blog.csdn.net/qq_42857603/article/details/82351864
 * @author kangqing
 * @since 2021/1/13 11:18
 */
public class RadixSort {
    public static void main(String[] args) {
        // 定义需要进行排序的数组
        int[] arr = {5, 7, 1, 13, 6, 2};
        // 下面进行基数排序

        sort(arr, Arrays.stream(arr).max().getAsInt());
        System.out.println(Arrays.toString(arr));
    }

    private static void sort(int[] arr, int max) {
        int len = String.valueOf(max).length();
        //从个位开始，对数组进行排序
        for (int k = 1; k <= len; k++) {
            //存储待排元素的临时数组
            int[] temp = new int[arr.length];
            //分桶个数
            int[] buckets = new int[10];

            //将数据出现的次数存储在buckets中
            for (int value : arr) {
                //(value / k) % 10 :value的最底位(个位)
                buckets[(value / k) % 10]++;
            }

            //更改buckets[i]，执行完此循环之后的buckets[i]就是第i个桶右边界的位置
            for (int i = 1; i < 10; i++) {
                buckets[i] += buckets[i - 1];
            }

            //将数据存储到临时数组temp中
            for (int i = arr.length - 1; i >= 0; i--) {
                temp[buckets[(arr[i] / k) % 10] - 1] = arr[i];
                buckets[(arr[i] / k) % 10]--;
            }

            //将有序元素temp赋给arr
            System.arraycopy(temp, 0, arr, 0, arr.length);

        }
    }
}
