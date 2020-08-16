package com.yunqing.demoatest.algorithm.array;

/**
 * LeetCode5185 存在连续三个奇数的数组
 * @author yunqing
 * @since 2020/8/16 22:47
 */
public class LeetCode5185 {
    public static void main(String[] args) {
        Solution5185 solution5185 = new Solution5185();
        var arr = new int[]{1, 2, 3};
        System.out.println(solution5185.threeConsecutiveOdds(arr));
    }
}

class Solution5185 {
    public boolean threeConsecutiveOdds(int[] arr) {
        int n = arr.length;
        if (n < 3) {
            return false;
        }
        for (int i = 1; i < n - 1; i++) {
            if (arr[i] % 2 != 1) {
                continue;
            }
            if (arr[i - 1] % 2 != 1) {
                continue;
            }
            if (arr[i + 1] % 2 != 1) {
                continue;
            }
            return true;
        }
        return false;
    }
}
