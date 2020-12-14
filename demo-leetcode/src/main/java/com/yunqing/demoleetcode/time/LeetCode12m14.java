package com.yunqing.demoleetcode.time;

import java.util.*;

/**
 * 12.14 HashMap
 * map.getOrDefault(key, new ArrayList<>());
 * @author yunqing
 * @since 2020/12/14 22:14
 */
public class LeetCode12m14 {
    public static void main(String[] args) {

    }
}

class Solution12m14 {
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        // key = 排好序的字符串， value = 排好序是key的字符串集合
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);
            List<String> list = map.getOrDefault(key, new ArrayList<>());
            list.add(str);
            map.put(key, list);
        }
        map.forEach((k, v) -> {
            res.add(v);
        });

        return res;
    }
}
