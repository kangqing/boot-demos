package com.yunqing.demoleetcode.time;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 12.10 双端队列
 * @author kangqing
 * @since 2020/12/10 10:07
 */
public class Time12m10 {
    public static void main(String[] args) {

    }
}

class Solution12m10 {
    public boolean lemonadeChange(int[] bills) {
        Deque<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < bills.length; i++) {
            if (bills[i] == 5) {
                // 当顾客拿出5元
                queue.offerLast(5);
            } else if (bills[i] == 10) {
                // 当顾客拿出10元
                if (queue.isEmpty()) return false;
                if (queue.peekLast() != 5) {
                    return false;
                } else {
                    queue.offerFirst(bills[i]);
                    queue.pollLast();
                }
            } else {
                // 否则只能是20元
                // 有10元先找零10 + 5， 否则尝试 5 + 5 + 5
                if (queue. isEmpty()) return false;
                if (queue.peekLast() != 5) {
                    return false;
                } else {
                    if (queue.peekFirst() == 10) {
                        queue.pollFirst();
                        queue.pollLast();
                    } else {
                        queue.pollLast();
                        if (queue.isEmpty()) return false;
                        if (queue.pollLast() == 5) {
                            if (queue.isEmpty()) return false;
                            if (queue.pollLast() != 5) return false;
                        } else {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
}
