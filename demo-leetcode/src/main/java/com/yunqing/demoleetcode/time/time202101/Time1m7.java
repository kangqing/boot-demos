package com.yunqing.demoleetcode.time.time202101;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 1.7 广度优先搜索、深度优先、并查集
 * @author kangqing
 * @since 2021/1/7 20:56
 */
public class Time1m7 {
    public static void main(String[] args) {

    }
}

class Solution1m7 {
    public int findCircleNum(int[][] isConnected) {
        int provinces = isConnected.length;
        boolean[] visited = new boolean[provinces];
        int circles = 0;
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < provinces; i++) {
            if (!visited[i]) {
                queue.offer(i);
                while (!queue.isEmpty()) {
                    int j = queue.poll();
                    visited[j] = true;
                    for (int k = 0; k < provinces; k++) {
                        if (isConnected[j][k] == 1 && !visited[k]) {
                            queue.offer(k);
                        }
                    }
                }
                circles++;
            }
        }
        return circles;
    }
}
