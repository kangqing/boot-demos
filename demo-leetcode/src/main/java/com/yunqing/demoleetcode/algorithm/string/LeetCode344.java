package com.yunqing.demoleetcode.algorithm.string;

/**
 * @author kangqing
 * @description LeetCode344 反转字符串
 * @date 2020/8/7 17:12
 */
public class LeetCode344 {
    public static void main(String[] args) {
        Solution344 solution344 = new Solution344();
        char[] s = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g'};
        solution344.reverseString(s);
        for (char c : s) {
            System.out.println(c);
        }
    }
}

class Solution344 {
    void reverseString(char[] s) {
        int a = 0; //头指针
        int z = s.length -1; //尾指针
        while(a < z) {
            char t = s[a];
            s[a] = s[z];
            s[z] = t;
            a++;
            z--;
        }
    }
}
