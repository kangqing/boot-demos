package com.yunqing.demoleetcode.algorithm.tree;

/**
 * LeetCode226 翻转二叉树
 * @author yx
 * @since 2020/9/17 14:39
 */
public class LeetCode226 {
    public static void main(String[] args) {

    }
}

class Solution {
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);
        root.left = right;
        root.right = left;
        return root;
    }
}
