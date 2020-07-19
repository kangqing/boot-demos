package com.yunqing.demoatest.algorithm.stack;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Description 逆波兰表达式求值
 * @Author yunqing
 * @Data 2020/7/19 12:09
 */
public class LeetCode150 {
    public static void main(String[] args) {
        String[] a = new String[]{"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"};
        Solution150 s = new Solution150();
        System.out.println(s.evalRPN(a));
    }
}

/**
 * 思路，数字入栈，遇到运算符，出栈两个，计算后继续入栈
 * 由于不会出现无解情况或者除数为0，所以最后栈中剩下的数就是结果
 */
class Solution150 {
    String[] arr = new String[]{"+", "-", "*", "/"};
    List<String> list = Arrays.asList(arr);
    public int evalRPN(String[] tokens) {
        //声明一个栈
        Deque<Integer> deque = new ArrayDeque<>();
        for (String token : tokens) {
            if (!list.contains(token)) {
                deque.push(Integer.parseInt(token));
            } else {
                Integer pop1 = deque.pop();
                Integer pop2 = deque.pop();
                switch (token) {
                    case "+" :
                        deque.push(pop2 + pop1);
                        break;
                    case "-" :
                        deque.push(pop2 - pop1);
                        break;
                    case "*" :
                        deque.push(pop2 * pop1);
                        break;
                    case "/" :
                        deque.push(pop2 / pop1);
                        break;
                }
            }
        }
        return deque.peek();
    }
}
