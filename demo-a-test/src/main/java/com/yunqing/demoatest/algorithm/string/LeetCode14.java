package com.yunqing.demoatest.algorithm.string;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @Description LeetCode14 最长公共前缀
 * @Author yx
 * @Data 2020/7/29 16:23
 */
public class LeetCode14 {
    public static void main(String[] args) {
        String[] arr = {"flower", "flow", "flight"};
        Solution14 s = new Solution14();
        System.out.println(s.longestCommonPrefix(arr));
    }
}

class Solution14 {
    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0 || Arrays.asList(strs).contains(null) || Arrays.asList(strs).contains("")) {
            return "";
        }
        //获取最短的字符串
        String s = Arrays.stream(strs).min(Comparator.comparing(String::length)).get();
        int len = s.length();
        for (int i = 0; i < len; i++) {
            String finalS = s;
            //用最短的字符串进行逐个比对，全以最短字符串开始则返回
            boolean b = Arrays.stream(strs).allMatch(e -> e.startsWith(finalS));
            if (b) {
                return s;
            }
            //否则最短字符串减去末尾一位再进行比较
            s = s.substring(0, s.length() - 1);
        }
        return "";
    }
}
