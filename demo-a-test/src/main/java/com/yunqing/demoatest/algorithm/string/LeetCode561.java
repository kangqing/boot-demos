package com.yunqing.demoatest.algorithm.string;

import java.util.*;

/**
 * @author yx
 * @description LeetCode561 数组拆分 I
 * @date 2020/8/7 17:42
 */
public class LeetCode561 {
    public static void main(String[] args) {

    }
}

class Solution561 {
    public int arrayPairSum(int[] nums) {
        List<Integer> list = Arrays.asList(nums);
        Collections.sort(list);
        int sum = 0;
        for (int i = 0; i < list.size(); i++) {
            if (i % 2 == 0) {
                sum += list.get(i);
            }
        }
    }
}
