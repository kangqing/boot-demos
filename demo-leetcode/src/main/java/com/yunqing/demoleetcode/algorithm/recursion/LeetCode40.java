package com.yunqing.demoleetcode.algorithm.recursion;

import java.util.*;
import java.util.stream.Collectors;

/**
 * LeetCode40 组合总和 II
 * @author yx
 * @since 2020/9/11 14:39
 */
public class LeetCode40 {
    public static void main(String[] args) {
        Solution40 solution40 = new Solution40();
        List<List<Integer>> lists = solution40.combinationSum(new int[]{10, 1, 2, 7, 6, 1, 5}, 8);
        for (List<Integer> list : lists) {
            list.forEach(System.out::print);
            System.out.println();
        }
    }
}

class Solution40 {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        //返回结果
        List<List<Integer>> res = new ArrayList<>();
        int len = candidates.length;
        if (len == 0) {
            return res;
        }
        //*********排序是剪枝提速的前提***************
        Arrays.sort(candidates);
        //用栈模拟跟节点到叶子节点的路径
        Deque<Integer> stack = new ArrayDeque<>();
        //回溯法进行递归调用
        hsf(candidates, target, len, res, stack, 0);
        // 由于会包含重复的解，所以去重
        return res.stream().distinct().collect(Collectors.toList());
    }

    private void hsf(int[] candidates, int target, int len, List<List<Integer>> res, Deque<Integer> stack, int begin) {
        // target == 0 说明刚好组合成target
        if (target == 0) {
            res.add(new ArrayList<>(stack));
            return;
        }
        for (int i = begin; i < len; i++) {
            //剪枝，此分支不可能组合成target了
            if (target - candidates[i] < 0) {
                break;
            }
            stack.push(candidates[i]);
            // 递归
            hsf(candidates, target - candidates[i], len, res, stack, i + 1);
            stack.pop();
        }
    }
}
