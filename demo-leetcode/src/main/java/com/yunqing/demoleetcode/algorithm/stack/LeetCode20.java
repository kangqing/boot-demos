package com.yunqing.demoleetcode.algorithm.stack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description LeetCode20 有效的括号
 * @Author yunqing
 * @Data 2020/7/18 5:32
 */
public class LeetCode20 {
    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.isValid("(]"));
    }
}

class Solution {
    /**
     * 验证输入的字符串满足：
     * 给定一个只包括 '('，')'，'{'，'}'，'['，']'的字符串，判断字符串是否有效。
     * 有效字符串需满足：
     * 左括号必须用相同类型的右括号闭合。
     * 左括号必须以正确的顺序闭合。
     * 注意空字符串可被认为是有效字符串。
     * 思路：
     *      利用栈来解决，先把对应的括号放进map中，遇到对应的左括号入栈，对应的右括号出栈
     * @param s
     * @return
     */
    boolean isValid(String s) {
        //单数必不可能通过验证
        if (s.length() % 2 > 0) {
            return false;
        }
        Map<Character, Character> map = new HashMap<>(3);
        map.put('(', ')');
        map.put('{', '}');
        map.put('[', ']');
        //双端队列，java不建议使用Stack栈，因为他继承Vector
        Deque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)) {
                stack.push(c);
            } else {
                if (!stack.isEmpty()) {
                    if (map.get(stack.peekFirst()).equals(c)) {
                        stack.pop();
                    }
                }
            }
        }
        return stack.isEmpty();
    }
}
