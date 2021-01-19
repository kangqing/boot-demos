package com.yunqing.demoleetcode.algorithm.tree;

/**
 * 构造一个二叉树
 * @author kangqing
 * @since 2020/9/17 14:40
 */
public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    TreeNode() {
    }

    public TreeNode(int x) {
        val = x;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
