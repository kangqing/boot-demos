package com.yunqing.demoleetcode.algorithm.recursion.huisu;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * LeetCode46 全排列
 * @author yx
 * @since 2020/9/18 14:07
 */
public class LeetCode46 {
    public static void main(String[] args) {
        Solution46 solution46 = new Solution46();
        List<List<Integer>> permute = solution46.permute(new int[]{1, 2, 3});
        System.out.println(permute);
    }
}

class Solution46 {
    public List<List<Integer>> permute(int[] nums) {
        int len = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        if (len == 0) return res;
        boolean[] used = new boolean[len];
        Deque<Integer> stack = new ArrayDeque<>();
        dfs(nums, len, stack, res, 0, used);
        return res;
    }

    private void dfs(int[] nums, int len, Deque<Integer> stack, List<List<Integer>> res, int begin, boolean[] used) {
        if (begin == len) {
            res.add(new ArrayList<>(stack));
            return;
        }

        for (int i = 0; i < len; i++) {
            if (used[i]) continue;
            used[i] = true;
            stack.push(nums[i]);

            dfs(nums, len, stack, res, begin + 1, used);

            used[i] = false;
            stack.pop();
        }
    }
}
