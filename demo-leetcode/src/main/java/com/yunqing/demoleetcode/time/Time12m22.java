package com.yunqing.demoleetcode.time;

import com.yunqing.demoleetcode.algorithm.tree.TreeNode;

import java.util.*;

/**
 * 12.22 广度优先遍历
 * @author yx
 * @since 2020/12/22 9:11
 */
public class Time12m22 {
    public static void main(String[] args) {

    }
}

class Solution12m22 {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean flag = true;
        while (!queue.isEmpty()) {
            Deque<Integer> deque = new ArrayDeque<>();
            int len = queue.size();
            for (int i = 0; i < len; i++) {
                TreeNode poll = queue.poll();
                if (flag) {
                    // 加在队列尾部
                    deque.offerLast(poll.val);
                } else {
                    // 加在队列头部
                    deque.offerFirst(poll.val);
                }
                // 判断当前二叉树是否有子节点
                if (poll.left!=null) {
                    queue.add(poll.left);
                }
                if (poll.right!=null) {
                    queue.offer(poll.right);
                }
            }
            res.add(new ArrayList<>(deque));
            flag = !flag;
        }
        return res;
    }
}