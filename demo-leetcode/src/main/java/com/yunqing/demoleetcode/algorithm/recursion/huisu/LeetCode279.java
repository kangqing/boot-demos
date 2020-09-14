package com.yunqing.demoleetcode.algorithm.recursion.huisu;

import java.util.*;

/**
 * 堆栈溢出自己写的
 * LeetCode279 完全平方数
 * @author yx
 * @since 2020/9/14 15:40
 */
public class LeetCode279 {
    public static void main(String[] args) {
        Solution279 solution279 = new Solution279();
        System.out.println(solution279.numSquares(7168));

        Solution279_1 solution279_1 = new Solution279_1();
        System.out.println(solution279_1.numSquares(7168));
    }
}

class Solution279 {
    public int numSquares(int n) {
        List<Integer> list = new ArrayList<>();
        int i = 1;
        while (i * i <= n) {
            list.add(i * i);
            i++;
        }
        //转化成了组合成目标数的题
        //记得那道题是回溯法
        //声明最小堆，用来存储组成目标数的元素个数
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        //双端队列模拟一个栈
        Deque<Integer> deque = new ArrayDeque<>();
        hsf(list, queue, deque, 0, n);
        return queue.peek();

    }

    private void hsf(List<Integer> list, PriorityQueue<Integer> queue, Deque<Integer> deque, int begin, int target) {
        if (target == 0) {
            queue.offer(deque.size());
            return;
        }
        for (int i = begin; i < list.size(); i++) {
            //剪枝，不可能组成目标数了
            if (target - list.get(i) < 0) {
                break;
            }
            if (!queue.isEmpty()) {
                if (queue.peek() == deque.size()) {
                    break;
                }
            }
            deque.push(list.get(i));
            //递归
            hsf(list, queue, deque, i, target - list.get(i));
            deque.pop();
        }
    }
}


class Solution279_1 {
    Set<Integer> square_nums = new HashSet<Integer>();

    protected boolean is_divided_by(int n, int count) {
        if (count == 1) {
            return square_nums.contains(n);
        }

        for (Integer square : square_nums) {
            if (is_divided_by(n - square, count - 1)) {
                return true;
            }
        }
        return false;
    }

    public int numSquares(int n) {
        this.square_nums.clear();

        for (int i = 1; i * i <= n; ++i) {
            this.square_nums.add(i * i);
        }

        int count = 1;
        for (; count <= n; ++count) {
            if (is_divided_by(n, count))
                return count;
        }
        return count;
    }
}
