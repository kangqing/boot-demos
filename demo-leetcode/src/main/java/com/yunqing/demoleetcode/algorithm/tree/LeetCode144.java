package com.yunqing.demoleetcode.algorithm.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode144 二叉树的前序遍历
 * @author yx
 * @since 2020/9/28 14:25
 *        1
 *     /    \
 *  null     2
 *         /
 *       3
 */
public class LeetCode144 {
    public static void main(String[] args) {
        TreeNode node = new TreeNode(1);
        node.left = null;
        node.right = new TreeNode(2);
        node.right.left = new TreeNode(3);
        Solution144 solution144 = new Solution144();
        List<Integer> list = solution144.preorderTraversal(node);
        list.forEach(System.out::println);
    }
}

/**
 * 递归的方法很容易，缺点是：如果树的层级很深，可能会造成堆栈溢出
 * 中序遍历：左  根  右
 * 前序遍历：根  左  右
 * 后序遍历：左  右  根
 */
class Solution144 {
    /**
     * 递归法
     * @param root 树
     * @return 前序遍历列表
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        loop(res, root);
        return res;
    }

    private void loop(List<Integer> res, TreeNode root) {
        if (root == null) {
            return;
        }
        res.add(root.val);
        loop(res, root.left);
        loop(res, root.right);
    }


}
