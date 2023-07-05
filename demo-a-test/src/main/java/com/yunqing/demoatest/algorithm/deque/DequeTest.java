package com.yunqing.demoatest.algorithm.deque;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 双端队列
 * @author kangqing
 * @since 2023/7/4 15:57
 */
@Slf4j
public class DequeTest {
    public static void main(String[] args) {
        Deque<String> deque = new ArrayDeque<>(1);
        deque.push("a");
        deque.push("b");
        deque.push("c");
        deque.push("d");

        deque.offerLast("e");
        deque.offerLast("f");
        deque.offerLast("g");
        deque.offerLast("h");

        deque.push("i");
        deque.offerLast("j");

        while (!deque.isEmpty()) {
            log.info("数据出栈 = {}", deque.pop());
        }
    }
}
