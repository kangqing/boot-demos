package com.yunqing.demoatest.algorithm.array;

import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode1577 数的平方等于两数乘积的方法数
 * @author yx
 * @date 2020/9/7 14:50
 */
public class LeetCode1577 {
    public static void main(String[] args) {
        Solution1577 s = new Solution1577();

        int[] nums1 = new int[]{43024,99908};
        int[] nums2 = new int[]{1864};
        System.out.println(s.numTriplets(nums1, nums2));
    }

}

/**
 * 利用Map存储乘积的个数
 * 注意考虑int溢出
 * 永远别三个for循环，必定超时
 */
class Solution1577 {
    public int numTriplets(int[] nums1, int[] nums2) {
        int res = 0;
        res += asd(nums1, nums2);
        res += asd(nums2, nums1);
        return res;
    }

    private int asd(int[] m, int[] n) {
        if (n.length == 1) {
            return 0;
        }
        int res = 0;
        Map<Long, Integer> map = new HashMap<>();
        for(int i = 0; i < n.length - 1; i++) {
            for(int j = i + 1; j < n.length; j++) {
                long lon = (long)n[i] * n[j];
                map.put(lon, map.getOrDefault(lon, 0) + 1);
            }
        }
        for(int a : m) {
            long lon = (long)a * a;
            if(map.containsKey(lon)) {
                res += map.get(lon);
            }
        }
        return res;
    }
}

