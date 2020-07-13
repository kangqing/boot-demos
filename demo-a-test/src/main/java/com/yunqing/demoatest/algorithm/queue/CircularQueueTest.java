package com.yunqing.demoatest.algorithm.queue;


import java.util.LinkedList;
import java.util.Queue;

/**
 * @Description 循环队列测试
 * @Author yunqing LeetCode 622 设计循环队列
 * @Data 2020/7/13 12:06
 */
public class CircularQueueTest {
    public static void main(String[] args) {
        QueueTest();
        System.out.println("-----------------------------------------------");
        MyCircularQueue queue = new MyCircularQueue(5);
        System.out.println(queue.isEmpty());
        System.out.println(queue.isFull());
        System.out.println(queue.enQueue(1));
        System.out.println(queue.isEmpty());
        System.out.println(queue.isFull());
        System.out.println(queue.Front() + "\t" + queue.Rear());
        System.out.println(queue.enQueue(2));
        System.out.println(queue.Front() + "\t" + queue.Rear());
        System.out.println(queue.enQueue(3));
        System.out.println(queue.Front() + "\t" + queue.Rear());
        System.out.println(queue.enQueue(4));
        System.out.println(queue.Front() + "\t" + queue.Rear());
        System.out.println(queue.enQueue(5));
        System.out.println(queue.Front() + "\t" + queue.Rear());
        System.out.println(queue.isEmpty());
        System.out.println(queue.isFull());
        System.out.println(queue.enQueue(6));
        System.out.println("出队列------------------------------------");
        System.out.println(queue.deQueue());
        System.out.println(queue.Front() + "\t" + queue.Rear());
        System.out.println(queue.deQueue());
        System.out.println(queue.Front() + "\t" + queue.Rear());
        System.out.println(queue.deQueue());
        System.out.println(queue.Front() + "\t" + queue.Rear());
        System.out.println(queue.deQueue());
        System.out.println(queue.Front() + "\t" + queue.Rear());
        System.out.println(queue.deQueue());
        System.out.println(queue.Front() + "\t" + queue.Rear());
    }

    private static void QueueTest() {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);
        queue.add(2);
        queue.offer(3);
        System.out.println(queue.peek());//返回元素的头
        queue.poll();
        System.out.println(queue.peek());
    }

}

class MyCircularQueue {

    private int[] array;
    private int head;
    private int tail;
    private int size;

    /** Initialize your data structure here. Set the size of the queue to be k. */
    public MyCircularQueue(int k) {
        array = new int[k];
        head = -1;
        tail = -1;
        size = k;
    }

    /** Insert an element into the circular queue. Return true if the operation is successful. */
    public boolean enQueue(int value) {
        if(isFull()) {
            return false;
        }
        if(isEmpty()) {
            head = 0;
        }
        tail = (tail + 1) % size;
        array[tail] = value;
        return true;
    }

    /** Delete an element from the circular queue. Return true if the operation is successful. */
    public boolean deQueue() {
        if(isEmpty()) {
            return false;
        }
        if(head == tail) {
            head = -1;
            tail = -1;
            return true;
        }
        head = (head + 1) % size;

        return true;
    }

    /** Get the front item from the queue. */
    public int Front() {
        if (isEmpty()) {
            return -1;
        }
        return array[head];
    }

    /** Get the last item from the queue. */
    public int Rear() {
        if (isEmpty()) {
            return -1;
        }
        return array[tail];
    }

    /** Checks whether the circular queue is empty or not. */
    public boolean isEmpty() {
        return head == -1;
    }

    /** Checks whether the circular queue is full or not. */
    public boolean isFull() {
        return ((tail + 1) % size) == head;
    }
}