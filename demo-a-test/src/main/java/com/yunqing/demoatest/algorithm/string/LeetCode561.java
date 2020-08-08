package com.yunqing.demoatest.algorithm.string;

import java.util.*;

/**
 * @author yx
 * @description LeetCode561 数组拆分 I
 * @date 2020/8/7 17:42
 */
public class LeetCode561 {
    public static void main(String[] args) {
        Solution561 solution561 = new Solution561();
        int[] nums = new int[]{10, 3, -1, 0, 2, 9, 11, -2};
        System.out.println("结果是：" + solution561.arrayPairSum(nums));
    }
}

class Solution561 {
    public int arrayPairSum(int[] nums) {
        List<Integer> list = new ArrayList<>();
        for (int num : nums) {
            list.add(num);
        }
        Collections.sort(list);
        list.forEach(System.out::println);
        int sum = 0;
        for (int i = 0; i < list.size(); i++) {
            if (i % 2 == 0) {
                sum += list.get(i);
            }
        }
        return sum;
    }
}
