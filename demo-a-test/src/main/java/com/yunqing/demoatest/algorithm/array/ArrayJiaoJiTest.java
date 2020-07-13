package com.yunqing.demoatest.algorithm.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description 数组交集
 * @Author yunqing
 * [9,4,9,8,4]
 * [4,9,5]
 * @Data 2020/7/13 16:32
 */
public class ArrayJiaoJiTest {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] a1 = {9,4,9,8,4};
        int[] a2 = {4,9,5};
        int[] intersect = solution.intersect(a1, a2);
        Arrays.stream(intersect).forEach(System.out::println);
    }


}

class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        for (int i : nums1) {
            list1.add(i);
        }
        for (int i : nums2) {
            list2.add(i);
        }
        int len = Math.min(list1.size(), list2.size());
        int[] newArr = new int[len];
        int index = 0;
        for(int i= 0; i < list1.size(); i++) {
            for(int j = 0; j < list2.size(); j++) {
                if(list1.get(i).equals(list2.get(j))) {
                    newArr[index++] = list1.get(i);
                    list1.remove(i);
                    list2.remove(j);
                    i--;
                    break;
                }
            }
        }
        int[] arr = new int[index];
        int a = 0;
        for (int i : newArr) {
            if(a < index) {
                arr[a] = i;
                a++;
            }
        }
        return arr;
    }


}
