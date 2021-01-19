package com.yunqing.demoleetcode.time;

import java.util.HashMap;
import java.util.Map;

/**
 * 12.23 hash表
 * @author kangqing
 * @since 2020/12/23 9:41
 */
public class Time12m23 {
    public static void main(String[] args) {

    }
}

class Solution12m23 {
    public int firstUniqChar(String s) {
        final char[] chars = s.toCharArray();
        Map<Character, Integer> map = new HashMap<>();
        for (char c : chars) {
            final Integer orDefault = map.getOrDefault(c, 0);
            map.put(c, orDefault + 1);
        }
        for (int i = 0; i < chars.length; i++) {
            final Integer integer = map.get(chars[i]);
            if (integer == 1) return i;
        }
        return -1;
    }
}
