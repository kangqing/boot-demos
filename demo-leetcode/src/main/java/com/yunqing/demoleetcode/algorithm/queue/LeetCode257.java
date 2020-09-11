package com.yunqing.demoleetcode.algorithm.queue;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * LeetCode257 二叉树的所有路径
 * author kangqing
 * date 2020/9/4 上午6:45
 */
public class LeetCode257 {
    public static void main(String[] args) {
        
    }
}


class Solution752 {
    public List<String> binaryTreePaths(TreeNode root) {
        //判空
        List<String> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        //广度优先搜索
        Deque<TreeNode> dTreeNode = new ArrayDeque<>();
        dTreeNode.offer(root);
        //维护一个String队列
        Deque<String> stringDeque = new ArrayDeque<>();
        stringDeque.offer(Integer.toString(root.val));
        while(!dTreeNode.isEmpty()) {
            TreeNode curr = dTreeNode.poll();
            String path = stringDeque.poll();
            if (curr.left == null && curr.right == null) {
                res.add(path);
            } else {
                if (curr.left != null) {
                    dTreeNode.offer(curr.left);
                    stringDeque.offer(path + "->" + curr.left.val);
                }
                if (curr.right != null) {
                    dTreeNode.offer(curr.right);
                    stringDeque.offer(path + "->" + curr.right.val);
                }
            }
        }
        return res;
    }

}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}