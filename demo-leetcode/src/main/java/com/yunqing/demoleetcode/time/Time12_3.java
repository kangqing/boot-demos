package com.yunqing.demoleetcode.time;

import java.util.Arrays;

/**
 * 12月3， 质数筛选
 * @author yunqing
 * @since 2020/12/3 20:50
 */
public class Time12_3 {
    public static void main(String[] args) {
        Solution12_3 s = new Solution12_3();
        System.out.println(s.countPrimes(1500000));
    }
}

class Solution12_3 {
    public int countPrimes(int n) {
        int[] arr = new int[n];
        // arr[i] = 1代表是质数
        Arrays.fill(arr, 1);
        // 质数个数
        int res = 0;
        for (int i = 2; i < n; i++) {
            // 如果是质数， + 1
            if (arr[i] == 1) {
                res++;
                // 质数的倍数全部不是质数
                if ((long) i * i < n) {
                    for (int j = i * i; j < n; j+=i) {
                        arr[j] = 0;
                    }
                }
            }
        }

        return res;
    }
}
