package com.yunqing.demoleetcode.topics2020;

import lombok.EqualsAndHashCode;

import java.util.HashMap;

/**
 * 复制一个随机链表，即获取该链表的深拷贝，Hash表存储已访问过的节点，避免死循环
 *
 * @author kangqing
 * @since 2021/2/1 9:40
 */
public class No6CopyRandomList {
    public static void main(String[] args) {

    }
}


// Definition for a Node.
@EqualsAndHashCode
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}

class SolutionNo6 {
    /**
     * hash表避免重复访问节点
     */
    HashMap<Node, Node> map = new HashMap<>();
    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        // 如果存在则返回节点
        if (map.containsKey(head)) {
            return map.get(head);
        }
        // 否则新建节点
        Node node = new Node(head.val);
        // 新建节点加入已访问过的hash表
        map.put(head, node);
        // 节点的两个指针
        node.next = this.copyRandomList(head.next);
        node.random = this.copyRandomList(head.random);
        return node;
    }
}
