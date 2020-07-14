package com.yunqing.demoatest.algorithm.queue;

import com.google.common.collect.Sets;

import java.util.*;

/**
 * @Description LeetCode 752 打开转盘锁
 * 思路：转盘转动一次可产生 8 种结果例如 0000 转动一次可产生如下 8 种结果
 * 1000 9000 上下转动第一位
 * 0100 0900 上下转动第二位
 * 0010 0090 上下转动第三位
 * 0001 0009 上下转动第四位
 *
 * @Author yunqing
 * @Data 2020/7/14 10:03
 */
public class TurntableLock {
    public static void main(String[] args) {
        Solution solution = new Solution();
        String[] arr = new String[] {"0201","0101","0102","1212","2002"};
        String target = "0202";
        int i = solution.openLock(arr, target);
        System.out.println("最少需要" + i + "步打开转盘锁");
    }

}

class Solution {
    public int openLock(String[] deadends, String target) {
        //定义初始转动次数为0
        int count = 0;
        List<String> list = Arrays.asList(deadends);
        //定义一个队列，用于逐个判断锁的字面是否等于目标值
        Queue<String> queue = new LinkedList<>();
        //用于存放访问过的转盘字面，出队列后进入set，防止重复访问无限循环
        //例如0000->0001向上转动第四位->0000向下转动第四位，防止这种情况发生
        Set<String> set = Sets.newHashSet();
        //转盘初始字面，入队列
        queue.offer("0000");
        set.add("0000");
        //如果队列不为空，逐个出队列
        while (!queue.isEmpty()) {
            int len = queue.size();
            for (int i = 0; i < len; i++) {
                String remove = queue.remove();
                if (list.contains(remove)) {
                    continue;
                }
                if (target.equals(remove)) {
                    return count;
                }
                for (int j = 0; j < 4; j++) {
                    //把拨动后的转盘数字加入队列
                    String up = upChangeLock(remove, j);
                    if (!set.contains(up)) {
                        queue.offer(up);
                        set.add(up);
                    }

                    String down = downChangeLock(remove, j);
                    if (!set.contains(down)) {
                        queue.offer(down);
                        set.add(down);
                    }
                }
            }
            count++;
        }
        return -1;
    }

    /**
     * 向上拨动转盘锁
     * @param number 转盘当前显示数字
     * @param i 拨动第几位转盘
     * @return
     */
    private String upChangeLock(String number, int i) {
        char[] chars = number.toCharArray();
        if (chars[i] == '9') {
            chars[i] = '0';
        } else {
            chars[i] += 1;
        }
        return new String(chars);
    }
    /**
     * 向下拨动转盘锁
     */
    private String downChangeLock(String number, int i) {
        char[] chars = number.toCharArray();
        if (chars[i] == '0') {
            chars[i] = '9';
        } else {
            chars[i] -= 1;
        }
        return new String(chars);
    }
}