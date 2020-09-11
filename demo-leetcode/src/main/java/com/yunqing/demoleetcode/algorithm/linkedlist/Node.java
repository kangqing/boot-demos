package com.yunqing.demoleetcode.algorithm.linkedlist;

/**
 * @Description
 * @Author yunqing
 * @Data 2020/7/8 10:20
 */
class Node<T> {
    Node<T> head = null; //第一个节点
    Node<T> next = null; //下一个节点
    T data; //节点数据

    private Node(T data) {
        this.data = data;
    }

    Node() {}

    /**
     * 在单链表末尾添加节点
     * @param data 节点数据
     */
    void addNodeAtEnd(T data) {
        Node<T> newNode = new Node<>(data);
        if (head == null) {
            head = newNode;
        } else {
            Node<T> temp = head; //设置临时节点为头结点
            while (temp.next != null) {
                //只要临时节点的下一个节点不为空，临时节点就指向下一个节点
                //直到下一个节点为空，把新节点放在结尾
                temp = temp.next;
            }
            temp.next = newNode;
        }
    }

    /**
     * 在单链表的开始位置添加节点
     * @param data 节点数据
     */
    void addNodeAtStart(T data) {
        Node<T> newNode = new Node<>(data);
        if (head == null) {
            head = newNode;
        } else {
            newNode.next = head;
            head = newNode;
        }
    }

    /**
     * 求单链表的节点数
     * @return
     */
    int length() {
        int length = 0;
        if (head == null) {
            return length;
        }
        Node<T> currNode = head;
        while (currNode != null) {
            length++;
            currNode = currNode.next;
        }
        return length;
    }

    /**
     * 打印单链表--隔开
     */
    void printLinedList() {
        Node<T> currNode = head;
        int i = 0;
        while (currNode != null) {
            if (i > 0) {
                System.out.print("--" + currNode.data);
            } else {
                System.out.print(currNode.data);
            }
            currNode = currNode.next;
            i++;
        }
        System.out.println();
    }
}
