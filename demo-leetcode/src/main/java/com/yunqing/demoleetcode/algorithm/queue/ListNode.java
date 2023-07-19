package com.yunqing.demoleetcode.algorithm.queue;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author kangqing
 * @since 2023/7/19 17:12
 */
public class ListNode {
    int val;
    ListNode next = null;
    public ListNode(int val) {
      this.val = val;
    }
}

class SolutionLianbiao {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     *
     * @param lists ListNode类ArrayList
     * @return ListNode类
     */
    public ListNode mergeKLists (ArrayList<ListNode> lists) {
        // write code here
        if(lists.size() == 0)
            return null;
        PriorityQueue<ListNode> queue = new PriorityQueue<>(Comparator.comparingInt(l -> l.val));
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        for(ListNode node:lists) {
            while (node != null) {
                queue.add(node);
                node = node.next;
            }
        }


        while(!queue.isEmpty()){
            tail.next = queue.poll();
            tail = tail.next;
        }
        tail.next = null;
        return dummy.next;

    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode node1 = new ListNode(2);
        ListNode node2 = new ListNode(6);
        ListNode node3 = new ListNode(4);

        head.next = node1;
        node1.next = node2;
        node2.next = node3;

        ListNode head1 = new ListNode(7);
        ListNode node11 = new ListNode(5);
        ListNode node21 = new ListNode(3);

        head1.next = node11;
        node11.next = node21;

        ArrayList<ListNode> list = new ArrayList<>();
        list.add(head);
        list.add(head1);
        SolutionLianbiao lianbiao = new SolutionLianbiao();
        ListNode res = lianbiao.mergeKLists(list);
        while (res != null) {
            System.out.println(res.val + "\t");
            res = res.next;
        }
    }
}
