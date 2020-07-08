package com.yunqing.demoatest;

/**
 * @Description
 * @Author yunqing
 * @Data 2020/7/8 10:20
 */
public class Node<T> {
    Node next = null; //下一个节点
    T data; //节点数据

    public Node(T data) {
        this.data = data;
    }
}
