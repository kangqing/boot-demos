package com.yunqing.demoleetcode.algorithm.linkedlist;

import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode133 重排链表
 * @author yunqing
 * @since 2020/10/20 7:13
 */
public class LeetCode133 {
    public static void main(String[] args) {
        ListNode node = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4))));

        Solution133 solution133 = new Solution133();
        solution133.reorderList(node);
    }
}

/**
 * 解决方案：由于链表不支持下标访问，所以决定将其放进List中
 */
class Solution133 {
    public void reorderList(ListNode head) {
        if (head == null) return;
        List<ListNode> list = new ArrayList<>();
        while (head != null) {
            list.add(head);
            head = head.next;
        }
        // 双指针
        int left = 0;
        int right = list.size() - 1;
        while (left < right) {
            // L1 -> Ln
            // L2 -> L(n - 1)
            list.get(left).next = list.get(right);
            left++;
            if (left == right) {
                break;
            }
            // Ln -> L2
            // L(n - 1) -> L3
            list.get(right).next = list.get(left);
            right--;
        }
        // 连表最后一个节点指向 null
        list.get(left).next = null;
    }
}



//Definition for singly-linked list.
class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
