package com.yunqing.demoleetcode.time;

import java.util.Arrays;

/**
 * 12.18 循环
 * @author yunqing
 * @since 2020/12/18 19:07
 */
public class Time12m18 {
    public static void main(String[] args) {
        Solution12m18 s = new Solution12m18();
        System.out.println(s.findTheDifference("aavc", "cvaaa"));
    }
}

class Solution12m18 {
    public char findTheDifference(String s, String t) {
        char[] ss = s.toCharArray();
        char[] tt = t.toCharArray();
        Arrays.sort(ss);
        Arrays.sort(tt);
        for (int i = 0; i < ss.length; i++) {
            if (ss[i] != tt[i]) {
                return tt[i];
            }
        }
        return tt[tt.length - 1];
    }
}