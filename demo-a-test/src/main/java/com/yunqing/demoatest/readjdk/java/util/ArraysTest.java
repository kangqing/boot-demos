package com.yunqing.demoatest.readjdk.java.util;

import java.util.Arrays;

/**
 * @author yx
 * @since 2020/9/15 17:28
 */
public class ArraysTest {
    public static void main(String[] args) {
        int[] arr = new int[]{3, 1, 3, 2, 4, 6, 5};
        System.out.println(Arrays.toString(arr));
        //针对基本数据类型   指定范围排序数组
        Arrays.sort(arr, 2, 5);
        System.out.println(Arrays.toString(arr));
        //针对基本数据类型   排序数组
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));

        //并行排序，区别于sort()的地方是，数据量大的时候此性能更好
        Arrays.parallelSort(arr);
        Arrays.parallelSort(arr, 2, 5);


    }
}
