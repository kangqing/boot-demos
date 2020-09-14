package com.yunqing.demoatest.readjdk.java.util;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

/**
 * @author yx
 * @description 双端队列
 * @date 2020/8/11 9:31
 */
public class ArrayDequeTest {

    private static Deque<Integer> deque = new ArrayDeque<>();

    public static void main(String[] args) {
        //stackTest();
        //queueTest();
        doubleQueueTest();
    }

    /**
     * 关于栈的使用
     */
    private static void stackTest() {
        System.out.println("--------------------栈------------------------");
        deque.clear();//清空
        for (int i = 0; i < 5; i++) {
            deque.push(i + 1);
        }
        System.out.println("栈的大小 = " + deque.size());
        //迭代器遍历，从栈顶开始
        for (Iterator<Integer> iterator = deque.iterator(); iterator.hasNext();) {
            System.out.print(iterator.next());
        }
        System.out.println("--------------------------------------------");
        //迭代器遍历，从栈底开始
        for (Iterator<Integer> iterator = deque.descendingIterator(); iterator.hasNext();) {
            System.out.print(iterator.next());
        }
        //出栈 5
        deque.pop();
        System.out.println("--------------------------------------------");
        deque.forEach(System.out::print);
        System.out.println("--------------------------------------------");
        //双端队列的移除出现的第一个匹配的元素特性，用于栈
        deque.removeFirstOccurrence(2);
        deque.forEach(System.out::print);


    }

    /**
     * 关于普通队列的使用
     * add() 等价于 offer() 同样是入队列，但是add()会在容量超限制抛异常
     * remove() 等价于 poll() 同样是出队列， 但是remove()会在队列为空抛异常
     * element() 等价于 peek() 同样是获取队列头元素， 但是element()会在队列为空抛异常
     *
     * *****建议使用offer()  poll()  peek()*****
     */
    private static void queueTest() {
        System.out.println("-------------------普通队列-------------------------");
        deque.clear();
        for (int i = 0; i < 5; i++) {
            deque.offer(i + 1); //入队列
        }
        deque.forEach(System.out::print);
        System.out.println("--------------------------------------------");
        for (Iterator<Integer> iterator = deque.descendingIterator(); iterator.hasNext();) {
            System.out.print(iterator.next());
        }
        deque.poll(); // 出队列
        System.out.println("--------------------------------------------");
        deque.forEach(System.out::print);
        System.out.println("--------------------------------------------");
        System.out.println(deque.element() + "------" + deque.peek());

    }

    /**
     * 双端队列的使用
     * addFirst() == offerFirst()
     * addLast() == offerLast()
     * removeFirst == pollFirst()
     * removeLast == pollLast()
     * getFirst() == peekFirst()
     * getLast() == peekLast()
     * 有无返回值、是否会异常等看具体实现，很简单的就不赘述
     * 建议使用后者带 offer/poll/peek的方法
     */
    private static void doubleQueueTest() {
        System.out.println("-----------------双端队列---------------------------");
        deque.clear();
        for (int i = 0; i < 3; i++) {
            deque.offerFirst(1);
            deque.offerFirst(i + 2);
        }
        deque.forEach(System.out::print);
        System.out.println("--------------------------------------------");
        deque.removeFirstOccurrence(1);
        deque.forEach(System.out::print);
        System.out.println("---------------本行是从头删除第一个匹配1的元素-----------------------------");
        deque.removeLastOccurrence(1);
        deque.forEach(System.out::print);
        System.out.println("---------------本行是从尾删除第一个匹配1的元素-----------------------------");

        ArrayDeque<Integer> deque1;
        deque1 = new ArrayDeque<>();
        for (int i = 0; i < 3; i++) {
            deque1.offerFirst(i);
        }
        /**
         * 确实是开辟了新的内存空间，而不是指针指向deque1
         */
        Deque<Integer> deque2 = deque1.clone();
        deque1.forEach(System.out::print);
        System.out.println("--------------------------------------------");
        deque2.forEach(System.out::print);
        System.out.println("--------------------------------------------");
        deque2.offerFirst(8);
        deque1.forEach(System.out::print);
        System.out.println("--------------------------------------------");
        deque2.forEach(System.out::print);
        System.out.println("--------------------------------------------");


    }
}
