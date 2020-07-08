package com.yunqing.demoatest;

/**
 * @Description
 * @Author yunqing
 * @Data 2020/7/8 9:47
 */
public class OneLinkedList {

    public static void main(String[] args) {
        Node node = new Node();
        node.addNodeAtEnd(1);
        node.printLinedList();
        node.addNodeAtEnd(2);
        node.printLinedList();
        node.addNodeAtStart(3);
        node.printLinedList();
        node.addNodeAtStart("head节点");
        node.addNodeAtEnd("tail节点");
        node.printLinedList();
        System.out.println(node.length());
        Node result = getKthFromEnd(node, 6);
        if (result == null) {
            System.out.println("不存在");
        } else {
            System.out.println(result.data);
        }
    }

    private static Node getKthFromEnd (Node node, int k) {
        if (node.length() < 1 || node.length() < k || k < 1) {
            return null;
        }
        if (node.length() == k) {
            return node.head;
        }
        Node first = node.head;
        Node second = node.head;
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

