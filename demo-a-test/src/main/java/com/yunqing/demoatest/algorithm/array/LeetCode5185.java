package com.yunqing.demoatest.algorithm.array;

/**
 * LeetCode5185 存在连续三个奇数的数组
 * @author yunqing
 * @since 2020/8/16 22:47
 */
public class LeetCode5185 {
    public static void main(String[] args) {
        Solution5185 solution5185 = new Solution5185();
        var arr = new int[]{1, 1, 2, 1, 3, 4, 2, 1, 3, 5};
        System.out.println(solution5185.threeConsecutiveOdds(arr));
    }
}

class Solution5185 {
    boolean threeConsecutiveOdds(int[] arr) {
        for (int i = 0; i < arr.length -2; i++) {
            if (arr[i] % 2 + arr[i + 1] % 2 + arr[i + 2] % 2 == 3) return true;
        }
        return false;
    }
}
