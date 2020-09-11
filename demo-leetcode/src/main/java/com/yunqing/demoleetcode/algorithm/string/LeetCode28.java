package com.yunqing.demoleetcode.algorithm.string;

/**
 * LeetCode28 实现Java中的indexOf()
 * 一个字符/字符串在另一个字符串中最先出现的位置‘
 * 子串为空返回 0
 * 子串不存在于父串中返回 -1
 * @author yunqing
 * @since 2020/8/6 23:16
 */
public class LeetCode28 {
    public static void main(String[] args) {
        String haystack = "Hello";
        String needle = "lo";
        Solution28 solution28 = new Solution28();
        System.out.println(solution28.strStr(haystack, needle));
    }
}

class Solution28 {
    public int strStr(String haystack, String needle) {
        //return haystack.indexOf(needle);
        if ("".equals(needle)) {
            return 0;
        }
        if (!haystack.contains(needle)) {
            return -1;
        }
        int len = needle.length();
        for (int i = 0; i < haystack.length() + 1 - len; i++) {
            String subStr = haystack.substring(i, i + len);
            if (subStr.equals(needle)) {
                return i;
            }
        }
        return -1;
    }
}
