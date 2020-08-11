package com.yunqing.demoatest.algorithm.linkedlist;

import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode119 杨辉三角 II
 * @author yunqing
 * @since 2020/8/11 21:48
 */
public class LeetCode119 {
}

class Solution119 {
    public List<Integer> getRow(int rowIndex) {
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i <= rowIndex + 1; i++) {
            List<Integer> addList = new ArrayList<>();
            for(int j = 0; j < i; j++) {
                if(j == 0 || j == i - 1) {
                    addList.add(1);
                }else{
                    addList.add(list.get(j - 1) + list.get(j));
                }
            }
            list = addList;
        }

        return list;
    }
}
