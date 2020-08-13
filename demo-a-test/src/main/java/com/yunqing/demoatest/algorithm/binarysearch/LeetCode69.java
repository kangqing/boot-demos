package com.yunqing.demoatest.algorithm.binarysearch;

/**
 * LeetCode69 x的平方根
 * @author yx
 * @date 2020/8/13 16:34
 */
public class LeetCode69 {
    public static void main(String[] args) {
        var s = new Solution69();
        System.out.println(s.mySqrt(2147395599));
    }
}

class Solution69 {
    int mySqrt(int x) {
        var left = 0;
        var right = x;
        var result = -1;
        while (left <= right) {
            var mid = left + (right - left) / 2;
            //注意这个long不加会报错，因为x很大的情况下mid * mid用int肯定不够存
            if ((long)mid * mid <= x) {
                result = mid;
                left = ++mid;
            }else {
                right = --mid;
            }
        }
        return result;
    }
}
