package com.yunqing.demoleetcode;

/**
 * 链表
 * @author kangqing
 * @since 2021/1/3 8:46
 */
public class ListNode {

    public int val;
    public ListNode next;

    public ListNode() {
    }

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
