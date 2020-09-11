package com.yunqing.demoleetcode.algorithm.linkedlist;

import java.io.Serializable;

/**
 * @Description 单链表
 * @Author yunqing
 * @Data 2020/7/8 9:47
 */
public class OneLinkedList {

    public static void main(String[] args) {
        Node<Serializable> node = new Node<>();
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
        Node<Serializable> result = getKthFromEnd(node, 5);
        if (result == null) {
            System.out.println("不存在");
        } else {
            System.out.println(result.data);
        }
    }

    /**
     * 求单链表的倒数第 k 个节点
     * @param node 节点
     * @param k 倒数第 k 位置，最后一位为倒数第一位
     * @return
     */
    private static Node<Serializable> getKthFromEnd (Node<Serializable> node, int k) {
        if (node.length() < 1 || node.length() < k || k < 1) {
            return null;
        }
        if (node.length() == k) {
            return node.head;
        }
        Node<Serializable> first = node.head;
        Node<Serializable> second = node.head;
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

