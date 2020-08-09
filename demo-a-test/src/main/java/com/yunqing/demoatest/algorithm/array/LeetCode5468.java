package com.yunqing.demoatest.algorithm.array;

import java.util.ArrayList;
import java.util.List;

/**
 * 周赛 LeetCode5468 第 k 个缺失的正整数
 * @author yunqing
 * @since 2020/8/9 22:45
 */
public class LeetCode5468 {
    public static void main(String[] args) {
        Solution5468 solution5468 = new Solution5468();
        System.out.println(solution5468.findKthPositive(new int[]{2,3,4,7,11}, 5));
    }
}

class Solution5468 {
    public int findKthPositive(int[] arr, int k) {
        List<Integer> list = new ArrayList<>();
        for(int a : arr) {
            list.add(a);
        }
        List<Integer> newList = new ArrayList<>();
        for(int i = 1; i<= 3000; i++) {
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
