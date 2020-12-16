package com.yunqing.demoleetcode.time;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 12.16 Hash表
 * @author yx
 * @since 2020/12/16 14:09
 */
public class Time12m16 {
    public static void main(String[] args) {
        Solution12m16 s = new Solution12m16();
        System.out.println(s.wordPattern("abba", "dog dog dog dog"));
    }
}

class Solution12m16 {
    public boolean wordPattern(String pattern, String s) {
        String[] str = s.split(" ");
        char[] chars = pattern.toCharArray();
        if (chars.length != str.length) return false;
        Map<Character, String> map = new HashMap<>();
        Set<String> set = new HashSet<>();
        for (int j = 0; j < chars.length; j++) {
            String res = map.getOrDefault(chars[j], "0");
            if ("0".equals(res)) {
                if (set.contains(str[j])) {
                    return false;
                }
                map.put(chars[j], str[j]);
                set.add(str[j]);
            } else {
                if (!res.equals(str[j])) {
                    return false;
                }
            }
        }
        return true;
    }
}
