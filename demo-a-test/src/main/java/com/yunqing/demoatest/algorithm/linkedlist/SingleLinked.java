package com.yunqing.demoatest.algorithm.linkedlist;

/**
 * @author kangqing
 * @since 2021/3/22 12:09
 */
public class SingleLinked {
    public static void main(String[] args) {
        MyNode myNode = new MyNode(1);
        MyNode n2 = myNode.next = new MyNode(2);
        MyNode n3 = n2.next = new MyNode(3);
        MyNode n4 = n3.next = new MyNode(4);
        MyNode n5 = n4.next = new MyNode(5);

        MyNode.print(myNode);

        MyNode fz = fz(myNode);

        System.out.println("----------");

        MyNode.print(fz);

    }

    // 反转单链表
    private static MyNode fz(MyNode node) {
        MyNode p = null;
        MyNode n = null;
        while (node != null) {
            n = node.next;
            node.next = p;
            p = node;
            node = n;
        }
        return p;
    }
}

class MyNode {
    int value;
    MyNode next;
    public MyNode(int x) {
        this.value = x;
    }

    public MyNode() {}

    // 打印单链表
    public static void print(MyNode node) {
        while (node != null) {
            System.out.println(node.value);
            node = node.next;
        }
    }


}
