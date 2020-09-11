package com.yunqing.demoleetcode.algorithm.array;

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
        System.out.println(solution5488.minOperations_1(5));
    }
}

class Solution5488 {
    int minOperations(int n) {
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

    /**
     * 看了大佬思路之后
     * @param n
     * @return
     */
    int minOperations_1(int n) {
        //先算最后的总数
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += 2 * i + 1;
        }
        //计算平均值
        int avg = sum / n;
        //计算每一个值跟平均值差的次数
        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans += Math.abs(2 * i + 1 - avg);
        }
        return ans / 2;
    }
}
