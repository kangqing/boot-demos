package com.yunqing.demoleetcode.offer;


import java.util.ArrayList;
import java.util.List;

/**
 * @author kangqing
 * @since 2020/12/24 17:45
 */
public class Offer06 {
    public static void main(String[] args) {

    }
}

class SolutionOffer06 {
    public int[] reversePrint(ListNode head) {
        List<Integer> list = new ArrayList<>();
        if (head == null) return new int[]{};
        while (head.next != null) {
            list.add(head.val);
            head = head.next;
        }
        list.add(head.val);
        int[] res = new int[list.size()];
        int index = 0;
        for (int i = list.size() - 1; i >= 0 ; i--) {
            res[index++] = list.get(i);
        }
        return res;
    }
}


class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}
