package com.yunqing.demoatest.algorithm.string;

/**
 * @author yunqing
 * @description LeetCode5 最长回文子串
 * @date 2020/8/4 20:54
 */
public class LeetCode5 {
    public static void main(String[] args) {
        Solution5 s = new Solution5();
        System.out.println(s.longestPalindrome("abcdd"));
    }
}

class Solution5 {
    public String longestPalindrome(String s) {
        if (s.length() < 2) {
            return s;
        }
        String result = s.substring(0, 1);
        int max = 1;
        for (int i = 0; i < s.length() - 1; i++) {
            //以当前索引节点作为中心点
            String str1 = getCurrMaxChar(s, i, i);
            //以当前索引和下一个索引中间的空隙作为中心点
            String str2 = getCurrMaxChar(s, i, i + 1);
            String maxChar = str1.length() > str2.length() ? str1 : str2;
            if (maxChar.length() > max) {
                max = maxChar.length();
                result = maxChar;
            }
        }
        return result;
    }

    /**
     * 获取当前索引为中心的最长回文子串
     * @param s 字符串
     * @param left 当前节点左边的索引
     * @param right 当前节点右边的索引
     * @return
     */
    private String getCurrMaxChar(String s, int left, int right) {
        int len = s.length();
        int m = left;
        int n = right;
        while (m >= 0 && n < len) {
            if (s.charAt(m) == s.charAt(n)) {
                m--;
                n++;
            }else {
                break;
            }
        }
        return s.substring(m + 1, n);
    }
}
