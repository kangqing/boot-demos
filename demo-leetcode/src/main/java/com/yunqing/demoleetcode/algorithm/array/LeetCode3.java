package com.yunqing.demoleetcode.algorithm.array;

import java.util.HashMap;
import java.util.Map;

/**
 * @author kangqing
 * @since 2023/2/16 21:20
 */
public class LeetCode3 {

    public static void main(String[] args) {
        final int abba = lengthOfLongestSubstring("abba");
        System.out.println(abba);
    }
    public static int lengthOfLongestSubstring(String s) {
        // 滑动窗口，无重复调整右边界，有重复时调整左边界
        int max = 0;
        int left = 0;
        int len = s.length();
        Map<Character, Integer> map = new HashMap<>();
        // 自动调整右指针
        for(int i = 0; i < len; i++) {
            if (map.containsKey(s.charAt(i))) {
                // 存在重复,调整左侧指针,防止左指针回调
                left = Math.max(map.get(s.charAt(i)) + 1, left);
            }
            max = Math.max(max, i - left + 1);
            map.put(s.charAt(i), i);
        }
        return max;
    }
}
