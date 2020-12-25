package com.yunqing.demoleetcode.offer;

import com.yunqing.demoleetcode.algorithm.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 分治法 + 递归
 * @author yx
 * @since 2020/12/25 14:58
 */
public class Offer07 {
    public static void main(String[] args) {
        final SolutionOffer07 s = new SolutionOffer07();
        System.out.println(s.buildTree(new int[]{3,9,20,15,7}, new int[]{9,3,15,20,7}));

    }
}

/**
 * 分治法，递归
 */
class SolutionOffer07 {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null) return null;
        List<Integer> pre = new ArrayList<>();
        List<Integer> in = new ArrayList<>();
        for (int i = 0; i < preorder.length; i++) {
            pre.add(preorder[i]);
            in.add(inorder[i]);
        }
        return recursion(pre, in);
    }

    private TreeNode recursion(List<Integer> pre, List<Integer> in) {
        if (in.isEmpty()) return null;
        // 删除前序遍历的第一个数，并得到它（即根节点）
        int root = pre.remove(0);
        // 在中序遍历中找到根节点的索引，索引左边是左子树，右边是右子树
        final int i = in.indexOf(root);
        // 创建二叉树
        TreeNode node = new TreeNode(root);
        // 递归构建左子树
        node.left = recursion(pre, in.subList(0, i));
        // 递归构建右子树
        node.right = recursion(pre, in.subList(i + 1, in.size()));
        return node;
    }
}
