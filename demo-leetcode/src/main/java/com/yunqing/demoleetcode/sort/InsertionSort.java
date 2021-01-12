package com.yunqing.demoleetcode.sort;

import java.util.Arrays;

/**
 * 插入排序 稳定：即不会破坏相同元素的原有顺序
 *
 * 时间复杂度 O(n²)
 *
 * @author kangqing
 * @since 2021/1/12 14:07
 */
public class InsertionSort {

    public static void main(String[] args) {
        // 定义需要进行排序的数组
        int[] arr = {5, 7, 1, 13, 6, 2};
        // 下面进行插入排序

        // 索引 i 之前的元素已经有序
        for (int i = 1; i < arr.length; i++) {
            // 获取索引 i 处的值，插入到前面已经有序的数组中
            int val = arr[i];
            int j;
            // 从当前位置往前数，如果大于 val 则索引往后移动一位，给 val 腾出合适位置放置
            for (j = i; j > 0 && arr[j - 1] > val ; j--) {
                arr[j] = arr[j - 1];
            }
            // 放到合适的位置
            arr[j] = val;
            System.out.println("第" + i + "趟插入值" + val + "排序后：" + Arrays.toString(arr));
        }
    }
}
