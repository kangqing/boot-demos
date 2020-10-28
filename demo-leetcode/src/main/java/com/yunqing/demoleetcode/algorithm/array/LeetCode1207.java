package com.yunqing.demoleetcode.algorithm.array;

import java.util.*;

/**
 * LeetCode1207 独一无二的出现次数
 * @author yx
 * @since 2020/10/28 17:43
 */
public class LeetCode1207 {
    public static void main(String[] args) {

    }
}

class Solution1207 {
    public boolean uniqueOccurrences(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int j : arr) {
            map.put(j, map.getOrDefault(j, 0) + 1);
        }
        Set<Integer> list = new HashSet<>();
        map.forEach((k, v) -> {
            list.add(v);
        });
        return list.size() == map.size();
    }
}
