package com.yunqing.demoleetcode.topics2020;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

/**
 * 最近最少使用缓存
 * @author kangqing
 * @since 2021/2/1 10:15
 */
public class No7LRUCache {
    public static void main(String[] args) {
        /**
         * LRUCache lRUCache = new LRUCache(2);
         * lRUCache.put(1, 1); // 缓存是 {1=1}
         * lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
         * lRUCache.get(1);    // 返回 1
         * lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
         * lRUCache.get(2);    // 返回 -1 (未找到)
         * lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
         * lRUCache.get(1);    // 返回 -1 (未找到)
         * lRUCache.get(3);    // 返回 3
         * lRUCache.get(4);    // 返回 4
         *
         * ["LRUCache","put","put","put","put","get","get"]
         * [[2],[2,1],[1,1],[2,3],[4,1],[1],[2]]
         */
        LRUCache lruCache = new LRUCache(2);
        lruCache.put(2, 1); // 缓存是 {1=1}
        lruCache.put(1, 1); // 缓存是 {1=1, 2=2}
        //System.out.println(lruCache.get(2));    // 返回 1
        lruCache.put(2, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
        //System.out.println(lruCache.get(2));    // 返回 -1 (未找到)
        lruCache.put(4, 1); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
        System.out.println(lruCache.get(1));    // 返回 -1 (未找到)
        System.out.println(lruCache.get(2));    // 返回 3
        //System.out.println(lruCache.get(4));    // 返回 4
    }
}

class LRUCache {
    // 双向链表
    static class Node {
        // key, value
        public int k, v;
        // 前一个节点 p, 后一个节点 n
        public Node p, n;
        // 构造函数
        public Node(int k, int v) {
            this.k = k;
            this.v = v;
        }
    }

    // 初始化容量
    private final int capacity;
    // 虚拟头节点
    private final Node head;
    // 虚拟尾结点
    private final Node tail;
    // 借助哈希表赋予了链表快速查找的特性
    private final Map<Integer, Node> map;

    // 构造函数初始化
    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
        head = new Node(-1, -1);
        tail = new Node(-1, -1);
        // 循环链表，建立头尾节点的关系
        head.n = tail;
        tail.p = head;
    }

    public int get(int key) {
        // 不存在则返回 -1
        if (!map.containsKey(key)) {
            return -1;
        }
        // 存在则获取节点
        Node node = map.get(key);
        moveToTail(node);
        return node.v;
    }

    public void put(int key, int value) {
        Node node = map.get(key);
        if (node == null) {
            // 如果 key 不存在，创建一个新的节点
            Node newNode = new Node(key, value);
            // 添加进哈希表
            map.put(key, newNode);
            // 添加至双向链表的尾部
            addToTail(newNode);
            if (map.size() > capacity) {
                // 如果超出容量，删除双向链表的头部节点
                Node tail = removeHead();
                // 删除哈希表中对应的项
                map.remove(tail.k);
            }
        } else {
            // 如果 key 存在，先通过哈希表定位，再修改 value，并移到尾部
            node.v = value;
            moveToTail(node);
        }

    }

    // 添加节点到队尾，即虚拟尾结点前面
    private void addToTail(Node node) {
        node.p = tail.p;
        node.n = tail;
        node.p.n = node;
        tail.p = node;
    }
    // 移动节点到队尾，即虚拟尾结点前面
    private void moveToTail(Node node) {
        removeNode(node);
        addToTail(node);
    }

    /**
     * 删除头部的最近最少使用的节点
     */
    private Node removeHead() {
        Node node = head.n;
        removeNode(node);
        return node;
    }

    /**
     * 删除任意节点
     * @param node 删除任意一个节点
     */
    private void removeNode(Node node) {
        node.p.n = node.n;
        node.n.p = node.p;
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
