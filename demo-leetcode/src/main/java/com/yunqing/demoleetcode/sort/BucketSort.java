package com.yunqing.demoleetcode.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 桶排序 稳定
 *
 * 时间复杂度 O(n + k)
 *
 * @author kangqing
 * @since 2021/1/13 11:04
 */
public class BucketSort {

    public static void main(String[] args) {
        // 定义需要进行排序的数组
        int[] arr = {5, 7, 1, 13, 6, 2};
        // 下面进行桶排序

        // 找出最大最小值
        int max = Arrays.stream(arr).max().getAsInt();
        int min = Arrays.stream(arr).min().getAsInt();

        // 规划桶数， 计算最多需要多少个桶
        int bucketNum = (max - min) / arr.length + 1;
        List<List<Integer>> bucketArr = new ArrayList<>(bucketNum);
        for(int i = 0; i < bucketNum; i++){
            bucketArr.add(new ArrayList<>());
        }

        //将每个元素放入桶
        for (int j : arr) {
            int num = (j - min) / (arr.length);
            bucketArr.get(num).add(j);
        }

        //对每个桶进行排序
        for (List<Integer> integers : bucketArr) {
            Collections.sort(integers);
        }

        System.out.println(bucketArr.toString());
    }
}
