package com.yunqing.demoatest.algorithm.string;

/**
 * LeetCode557 反转字符串中的单词 III
 * @author yunqing
 * @since 2020/8/11 21:52
 */
public class LeetCode557 {
    public static void main(String[] args) {
        Solution557 solution557 = new Solution557();
        System.out.println(solution557.reverseWords("Let's take LeetCode contest"));
    }
}

class Solution557 {
    public String reverseWords(String s) {
        String[] s1 = s.split(" ");
        int index = 0;
        for (String s2 : s1) {
            StringBuilder sb = new StringBuilder(s2);
            s1[index++] = sb.reverse().toString();
        }
        return String.join(" ", s1);
    }
}
