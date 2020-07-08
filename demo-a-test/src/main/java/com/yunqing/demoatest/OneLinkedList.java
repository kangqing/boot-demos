package com.yunqing.demoatest;

/**
 * @Description
 * @Author yunqing
 * @Data 2020/7/8 9:47
 */
public class OneLinkedList {

    public static void main(String[] args) {
        ListNode listNode = new ListNode();
        listNode.addNodeAtEnd(1);
        listNode.printLinedList();
        listNode.addNodeAtEnd(2);
        listNode.printLinedList();
        listNode.addNodeAtStart(3);
        listNode.printLinedList();
        listNode.addNodeAtStart("head节点");
        listNode.addNodeAtEnd("tail节点");
        listNode.printLinedList();
        System.out.println(listNode.length());
        Node node = getKthFromEnd(listNode, 6);
        if (node == null) {
            System.out.println("不存在");
        } else {
            System.out.println(node.data);
        }
    }

    private static Node getKthFromEnd (ListNode listNode, int k) {
        if (listNode.length() < 1 || listNode.length() < k || k < 1) {
            return null;
        }
        if (listNode.length() == k) {
            return listNode.head;
        }
        Node first = listNode.head;
        Node second = listNode.head;
        for (int i = 0; i < k; i++) {
            first = first.next;
        }
        while (first.next != null) {
            first = first.next;
            second = second.next;
        }
        return second.next;
    }
}

