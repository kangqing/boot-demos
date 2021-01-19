package com.yunqing.demoleetcode.algorithm.recursion.huisu;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * LeetCode216 组合总和 III
 * @author kangqing
 * @since 2020/9/11 15:03
 */
public class LeetCode216 {
    public static void main(String[] args) {
        Solution216 solution216 = new Solution216();
        List<List<Integer>> lists = solution216.combinationSum3(3, 9);
        for (List<Integer> list : lists) {
            list.forEach(System.out::print);
            System.out.println();
        }

    }
}

class Solution216 {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        // 因为组合只能有 1-9的数字且不重复，直接构造一个1 - 9 的数组
        int[] arr = new int[]{1,2,3,4,5,6,7,8,9};
        // 回溯法走起
        Deque<Integer> stack = new ArrayDeque<>();
        hsf(k, n, res, stack, 0, arr);
        return res;
    }

    /**
     * 回溯法
     * @param k k 个数
     * @param n 和为 n
     * @param res 返回结果
     * @param stack 栈
     * @param begin 开始索引
     * @param arr 数组
     */
    private void hsf(int k, int n, List<List<Integer>> res, Deque<Integer> stack, int begin, int[] arr) {
        if (n == 0) {
            if (stack.size() == k) {
                res.add(new ArrayList<>(stack));
            }
            return;
        }
        // 剪枝
        for (int i = begin; i < arr.length; i++) {
            if (n - arr[begin] < 0) {
                break;
            }
            stack.push(arr[i]);
            hsf(k, n - arr[i], res, stack, i + 1, arr);
            stack.pop();
        }
    }
}
