package com.yunqing.demoleetcode.time.time202101;


import com.yunqing.demoleetcode.ListNode;


/**
 * 1.3 链表
 *
 * 输入：head = 1->4->3->2->5->2, x = 3
 * 输出：       1->2->2->4->3->5
 * @author kangqing
 * @since 2021/1/3 8:44
 */
public class Time1m3 {
    public static void main(String[] args) {
        Solution1m3 s = new Solution1m3();
        ListNode node = new ListNode(1);
        ListNode node2 = new ListNode(4);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(2);
        ListNode node5 = new ListNode(5);
        ListNode node6 = new ListNode(2);
        node.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;

        ListNode partition = s.partition(node, 3);
        while (partition != null) {
            System.out.println(partition.val);
            partition = partition.next;
        }
    }
}

class Solution1m3 {
    public ListNode partition(ListNode head, int x) {
        ListNode small = new ListNode(0);
        ListNode smallHead = small;
        ListNode large = new ListNode(0);
        ListNode largeHead = large;
        while (head != null) {
            if (head.val < x) {
                small.next = head;
                small = small.next;
            } else {
                large.next = head;
                large = large.next;
            }
            head = head.next;
        }
        large.next = null;
        small.next = largeHead.next;
        return smallHead.next;
    }
}
