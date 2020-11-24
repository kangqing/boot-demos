package com.yunqing.demoleetcode.algorithm.queue;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * LeetCode222 完全二叉树的节点个数
 * @author yunqing
 * @since 2020/11/24 20:48
 */
public class LeetCode222 {
    public static void main(String[] args) {

    }
}

class Solution {
    public int countNodes(TreeNode root) {
        int res = 0;
        if (root == null) return res;
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode poll = queue.poll();
            res++;
            if (poll.left != null) {
                queue.offer(poll.left);
            }
            if (poll.right != null) {
                queue.offer(poll.right);
            }
        }
        return res;

    }
}
