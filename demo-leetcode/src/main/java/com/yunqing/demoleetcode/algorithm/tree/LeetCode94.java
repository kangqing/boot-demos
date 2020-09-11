package com.yunqing.demoleetcode.algorithm.tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * LeetCode94 二叉树的中序遍历
 * @Author yunqing
 * @Date 2020/7/20 6:17
 */
public class LeetCode94 {
    public static void main(String[] args) {
        /**
         * 构造二叉树【1, null, 2, 3】
         */
        TreeNode node = new TreeNode(1);
        node.left = null;
        node.right = new TreeNode(2);
        node.right.left = new TreeNode(3);

        Solution94_1 solution94_1 = new Solution94_1();
        List<Integer> list = solution94_1.inorderTraversal(node);
        list.forEach(System.out::println);

        Solution94_2 solution94_2 = new Solution94_2();
        solution94_2.inorderTraversal(node).forEach(System.out::println);
    }
}

/**
 * 二叉树节点
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

/**
 * 递归的方法很容易，缺点是：如果树的层级很深，可能会造成堆栈溢出
 * 中序遍历：左  根  右
 */
class Solution94_1 {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        loop(list, root);
        return list;
    }
    //递归进行中序遍历
    private void loop(List<Integer> list, TreeNode node) {
        if (node.left != null) {
            loop(list, node.left);
        }
        list.add(node.val);
        if (node.right != null) {
            loop(list, node.right);
        }
    }
}

/**
 * 基于栈的遍历
 * 思路：
 *      如果当前节点不为空，入栈，当前节点 = 当前左节点； 循环直到获取到最左的节点
 *      出栈即是最左子节点，加入集合，当前节点 = 当前节点的右节点，如果当前右节点为 null 则出栈下一个
 *      即外层循环条件为 root != null 或者 栈不为空
 */
class Solution94_2 {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Deque<TreeNode> deque = new ArrayDeque<>();
        while (root != null || !deque.isEmpty()) {
            while (root != null) {
                deque.push(root);
                root = root.left;
            }
            root = deque.pop();
            list.add(root.val);
            root = root.right;
        }
        return list;
    }
}