package com.yunqing.demoleetcode.algorithm.recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * 找到最终的安全状态
 * 递归，深度优先搜索
 * @author kangqing
 * @since 2021/8/5 21:39
 */
public class LeetCode802 {
    public static void main(String[] args) {
        int[][] arr = new int[][]{{1, 2}, {2, 3}, {5}, {0}, {5}, {}, {}};
        Solution802 solution802 = new Solution802();
        System.out.println(solution802.eventualSafeNodes(arr));
    }


}

class Solution802 {
    public List<Integer> eventualSafeNodes(int[][] graph) {
        List<Integer> ans = new ArrayList<>();
        int len = graph.length;
        int[] colors = new int[len];
        for (int i = 0; i < len; i++) {
            // 给节点上色，判断是否安全
            if (setColorIsSafe(graph, colors, i)) {
                ans.add(i);
            }
        }

        return ans;
    }

    /**
     * 如果节点未被访问则默认是 0
     * 如果节点被访问处于递归栈中或者处于一个环中，则为 1
     * 如果搜索完毕，节点安全，则是 2
     * @param graph 二维数组
     * @param colors 一维数组，小球被访问的情况
     * @param i 第几个
     * @return 是否安全
     */
    private boolean setColorIsSafe(int[][] graph, int[] colors, int i) {
        // 如果这个节点 > 0,则被访问过， 判断是否 == 2，即是否安全
        if (colors[i] > 0) {
            return colors[i] == 2;
        }
        // 没被访问，则设置 = 1，即本次访问过了
        colors[i] = 1;
        // 循环 二维数组的的第 i 个子数组，挨个去访问
        for (int y : graph[i]) {
            // 如果不安全则返回false
            if (!setColorIsSafe(graph, colors, y)) {
                return false;
            }
        }
        // 安全则colors = 2
        colors[i] = 2;
        return true;
    }
}
