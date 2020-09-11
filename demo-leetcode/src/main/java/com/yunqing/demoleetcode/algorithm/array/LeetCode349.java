package com.yunqing.demoleetcode.algorithm.array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * LeetCode349 两个数组的交集
 * @author yunqing
 * @since 2020/8/21 6:50
 */
public class LeetCode349 {
    public static void main(String[] args) {
        int[] arr1 = new int[]{1, 2, 4, 4};
        int[] arr2 = new int[]{1, 2, 4, 5};
        Solution349 solution349 = new Solution349();
        System.out.println(Arrays.toString(solution349.intersection(arr1, arr2)));
    }
}

class Solution349 {
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        int[] res = new int[Math.max(nums1.length, nums2.length)];
        int index = 0;
        for(Integer i : nums1) {
            set.add(i);
        }
        for (Integer i : nums2) {
            if (set.contains(i)) {
                res[index++] = i;
                set.remove(i);
            }
        }
        return Arrays.stream(res).limit(index).toArray();
    }
}
