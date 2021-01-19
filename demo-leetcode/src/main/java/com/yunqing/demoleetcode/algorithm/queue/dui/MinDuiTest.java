package com.yunqing.demoleetcode.algorithm.queue.dui;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 题目：在10万个数中找出最大的10个
 * 分析：使用最小堆进行解析，
 * 选取前10个元素构造一个最小堆，然后用剩下的元素和堆顶元素比较，如果小于堆顶元素，则小于10堆中所有元素，丢弃，
 * 如果大于堆顶元素，替换
 * @author kangqing
 * @since 2020/9/14 9:04
 */
public class MinDuiTest {
    public static void main(String[] args) {
        //用三十个元素模拟10万元素
        int[] arr = new int[]{1,11,12,5,6,23,34,1,0,4,
                122,121,55,56,0,45,677,876,543,53,
                5345,6,90,87,654,32,5,7,43,3};
        //默认最小堆
        PriorityQueue<Integer> minQueue = new PriorityQueue<>();
        //可以这样改造成最大堆
        PriorityQueue<Integer> maxQueue = new PriorityQueue<>((m, n) -> n - m);
        //前10个放进最小堆
        for (int i = 0; i < 10; i++) {
            minQueue.offer(arr[i]);
        }
        for (int i = 10; i < arr.length; i++) {
            Integer integer = minQueue.peek();
            if (arr[i] > integer) {
                minQueue.poll();
                minQueue.offer(arr[i]);
            }
        }
        for (int i = 0; i < 10; i++) {
            System.out.print(minQueue.poll() + "\t");
        }
        System.out.println();
        Arrays.sort(arr);
        for (int i = 20; i < 30; i++) {
            System.out.print(arr[i] + "\t");
        }

    }

}
