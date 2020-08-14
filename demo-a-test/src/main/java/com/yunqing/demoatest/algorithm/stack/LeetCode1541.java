package com.yunqing.demoatest.algorithm.stack;

import java.util.ArrayDeque;
import java.util.Iterator;

/**
 * LeetCode1541 平衡括号字符串的最少插入次数
 * @author yx
 * @date 2020/8/14 16:52
 */
public class LeetCode1541 {
    public static void main(String[] args) {
        Solution1541 solution1541 = new Solution1541();
        var s = "(()))";
        System.out.println(solution1541.minInsertions(s));
    }
}
0000000000000000000000000000000000000000000000000000000000000000000000000

/**
 * 因为一个( 需要匹配 ))
 * 所以加入一个)先令其为 ] 再有一个才升级为 )
 * 这样 ()就能对应了
 */
class Solution1541 {
    public int minInsertions(String s) {
        int count = 0;
        var stack = new ArrayDeque<Character>();
        char[] chars = s.toCharArray();
        for (char c : chars) {
            if (c == '(') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    stack.push(']');
                } else if (stack.peek() == ']'){
                    stack.pop();
                    stack.push(c);
                } else {
                    stack.push(']');
                }
            }
        }
        //创建一个新的栈，开始把对应的()删除
        var stack1 = new ArrayDeque<Character>();
        Iterator<Character> iterator = stack.descendingIterator();
        while (iterator.hasNext()) {
            var c = iterator.next();
            if (stack1.isEmpty()) {
                stack1.push(c);
                continue;
            }
            var peek = stack1.peek();
            if (c == '(') {
                stack1.push(c);
            }else if (c == ')'){
                if (peek == '(') {
                    stack1.pop();
                }else{
                    stack1.push(')');
                }
            } else {
                stack1.push(')');
                count++;
            }
        }

        if (stack1.isEmpty()) {
            return count;
        }

        while (!stack1.isEmpty()) {
            var c = stack1.pop();
            if (c == '(') {
                count += 2;
            } else {
                count++;
            }
        }
        return count;


    }
}
