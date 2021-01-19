package com.yunqing.demoleetcode.algorithm.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Leetcode145 二叉树的后序遍历
 * @author kangqing
 * @since 2020/9/28 15:00
 *  *        1
 *  *     /    \
 *  *  null     2
 *  *         /
 *  *       3
 */
public class Leetcode145 {
    public static void main(String[] args) {
        TreeNode node = new TreeNode(1);
        node.left = null;
        node.right = new TreeNode(2);
        node.right.left = new TreeNode(3);
        Solution145 solution145 = new Solution145();
        List<Integer> list = solution145.postorderTraversal(node);
        list.forEach(System.out::println);
    }
}

class Solution145 {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        loop(res, root);
        return res;
    }

    private void loop(List<Integer> res, TreeNode root) {
        if (root == null) {
            return;
        }
        loop(res, root.left);
        loop(res, root.right);
        res.add(root.val);
    }
}

