package com.yunqing.demoatest.algorithm.linkedlist;

import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode118 杨辉三角
 * @author yunqing
 * @since 2020/8/11 21:25
 */
public class LeetCode118 {
    public static void main(String[] args) {
        Solution118 solution118 = new Solution118();
        List<List<Integer>> generate = solution118.generate(5);
        for (List<Integer> list : generate) {
            list.forEach(System.out::print);
            System.out.println();
        }

    }
}

class Solution118 {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        for(int i = 1; i <= numRows; i++) {
            List<Integer> addList = new ArrayList<>();
            for(int j = 0; j < i; j++) {
                if(j == 0 || j == i - 1) {
                    addList.add(1);
                }else{
                    addList.add(list.get(j - 1) + list.get(j));
                }
            }
            result.add(addList);
            list = addList;
        }

        return result;
    }
}
