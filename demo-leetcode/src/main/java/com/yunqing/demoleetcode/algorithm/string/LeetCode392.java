package com.yunqing.demoleetcode.algorithm.string;

/**
 * @Description LeetCode392 判断子序列
 * @Author yx
 * @Data 2020/7/27 15:45
 */
public class LeetCode392 {
    public static void main(String[] args) {
        Solution392 solution392 = new Solution392();
        String s = "aaaaaa";
        String t = "bbaaaa";
        System.out.println(solution392.isSubsequence(s, t));
    }
}

/**
 * 判断 s 是否是 t 的子序列
 */
class Solution392 {
    public boolean isSubsequence(String s, String t) {
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            //返回当前字符在 t 字符串中第一次出现的位置索引
            int tIndex = t.indexOf(chars[i]);
            if (tIndex == -1) {
                return false;
            }
            t = t.substring(tIndex + 1);
        }
        return true;
    }
}
