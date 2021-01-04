package com.yunqing.demoleetcode.time.time202101;

/**
 * 1.4 递归 菲波那切数列
 * 1.4 动态规划-滚动数组 菲波那切数列
 * @author yx
 * @since 2021/1/4 9:19
 */
public class Time1m4 {
    public static void main(String[] args) {
        final Solution1m4 s = new Solution1m4();
        System.out.println(s.fib(10));

        final Solution1m4_dp s_dp = new Solution1m4_dp();
        System.out.println(s_dp.fib(10));
    }
}

/**
 * 递归解法
 */
class Solution1m4 {

    static IntCall fact;

    //Fibonacci 序列中的最后两个元素求和来产生下一个元素
    Solution1m4() {
        fact = n -> n == 0 ? 0 :
                n == 1 ? 1 :
                        fact.call(n - 1) + fact.call(n - 2);
    }
    public int fib(int n) {
        return fact.call(n);
    }
}

/**
 * 动态规划，滚动数组解法
 */
class Solution1m4_dp {

    public int fib(int n) {
        if (n < 2) return n;
        int x = 0, y = 0, z = 1;
        for (int i = 2; i <= n ; i++) {
            x = y;
            y = z;
            z = x + y;
        }
        return z;
    }
}

/**
 * 函数式接口
 */
@FunctionalInterface
interface IntCall {
    int call(int arg);
}
