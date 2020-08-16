package com.yunqing.demoatest.algorithm.array;

import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode5488 使数组中所有元素相等的最小操作数
 * @author yunqing
 * @since 2020/8/16 22:49
 */
public class LeetCode5488 {
    public static void main(String[] args) {
        Solution5488 solution5488 = new Solution5488();
        System.out.println(solution5488.minOperations(5));
    }
}

class Solution5488 {
    public int minOperations(int n) {
        //第一位是1
        //最后一位是
        int first = 1;
        int last = 2 * (n - 1) + 1;
        //最后相同的值是
        int res = (last + first) / 2;
        List<Integer> list = new ArrayList<>();
        int d = 1;
        while (d < res) {
            list.add(d);
            d += 2;
        }
        int count = 0;
        for (Integer integer : list) {
            count += res - integer;
        }
        return count;
    }
}
