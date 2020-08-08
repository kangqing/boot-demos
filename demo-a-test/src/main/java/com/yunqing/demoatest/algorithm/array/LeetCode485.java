package com.yunqing.demoatest.algorithm.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * LeetCode485 最大连续1的个数
 * @author yunqing
 * @since 2020/8/8 22:23
 */
public class LeetCode485 {
    public static void main(String[] args) {
        int[] arr = new int[]{1,2,3,4};
        Solution111 solution111 = new Solution111();
        System.out.println(solution111.findKthPositive(arr, 2));
    }
}

/**
 * 思路：快慢指针解法
 */
class Solution111 {
    public int findKthPositive(int[] arr, int k) {
        List<Integer> list = new ArrayList<>();
        for(int a : arr) {
            list.add(a);
        }
        List<Integer> newList = new ArrayList<>();
        for(int i = 1; i<= 1000; i++) {
            if(!list.contains(i)) {
                newList.add(i);
            }
            if(newList.size() > k){
                break;
            }
        }
        return newList.get(k -1);
    }
}
