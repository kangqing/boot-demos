package com.yunqing.demoleetcode.algorithm.string;


/**
 * LeetCode1370 上升下降字符串
 * @author yunqing
 * @since 2020/11/25 20:40
 */
public class LeetCode1370 {
    public static void main(String[] args) {
        Solution1370 solution1370 = new Solution1370();
        System.out.println(solution1370.sortString("aaaabbbbcccc"));
    }
}

class Solution1370 {
    public String sortString(String s) {
        // 用来存储每个字母的数量
        int[] num = new int[26];
        for (int i = 0; i < s.length(); i++) {
            // 对应字母的数量 + 1
            num[s.charAt(i) - 'a']++;
        }
        StringBuilder sb = new StringBuilder();
        while (sb.length() != s.length()) {
            for (int i = 0; i < 26; i++) {
                if (num[i] > 0) {
                    // 有此字母的数字
                    sb.append((char) (i + 'a'));
                    num[i]--;
                }
            }
            for (int i = 25; i >= 0 ; i--) {
                if (num[i] > 0) {
                    // 有此字母的数字
                    sb.append((char) (i + 'a'));
                    num[i]--;
                }
            }
        }
        return sb.toString();

    }
}
