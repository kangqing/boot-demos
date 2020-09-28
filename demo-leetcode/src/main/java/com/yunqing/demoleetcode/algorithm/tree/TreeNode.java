package com.yunqing.demoleetcode.algorithm.tree;

/**
 * 构造一个二叉树
 * @author yx
 * @since 2020/9/17 14:40
 */
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int x) {
        val = x;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
