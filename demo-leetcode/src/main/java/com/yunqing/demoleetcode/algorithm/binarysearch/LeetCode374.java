package com.yunqing.demoleetcode.algorithm.binarysearch;

import java.util.Random;

/**
 * LeetCode374 猜数字大小
 * @author yunqing
 * @since 2020/8/13 21:21
 */
public class LeetCode374 {
    public static void main(String[] args) {
        Solution374 solution374 = new Solution374();
        System.out.println(solution374.guessNumber(1000));
    }
}

class Solution374 extends GuessGame {
    public int guessNumber(int n) {
        var left = 1;
        var right = n;
        while (left <= right) {
            var mid = left + (right - left) / 2;
            if (guess(mid) == 0) {
                return mid;
            }else if (guess(mid) == 1){
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }
}

/**
 * 接口实现类
 */
class GuessGame implements Guess{

    static final int x = new Random().nextInt(1000) + 1;

    static {
        System.out.println("猜测是数字是 = " + x);
    }
    @Override
    public int guess(int num) {
        return Integer.compare(x, num);
    }
}

/**
 * 定义接口
 */
interface Guess {
    /**
     * 猜数字接口
     * @param num
     * @return
     */
    int guess(int num);
}
