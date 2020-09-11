package com.yunqing.demoleetcode.algorithm.stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 周赛 LeetCode5483 整理字符串
 * @author yunqing
 * @since 2020/8/9 22:36
 */
public class LeetCode5483 {
    public static void main(String[] args) {
        Solution5483 solution5483 = new Solution5483();
        System.out.println(solution5483.makeGood("leEeetcode"));
    }
}

class Solution5483 {
    //大写字母=小写字母-32
    public String makeGood(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (stack.isEmpty()) {
                stack.push(c);
            }else {
                Character peek = stack.peek();
                if (peek == c - 32 || c == stack.peek() - 32) {
                    stack.pop();
                }else {
                    stack.push(c);
                }
            }
        }
        if (stack.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        sb.reverse();
        return sb.toString();
    }
}
