package com.yunqing.demoleetcode.algorithm.array;

import java.util.*;

/**
 * LeetCode973 最接近原点的 K 个点
 * @author yunqing
 * @since 2020/11/9 20:15
 */
public class LeetCode973 {
    public static void main(String[] args) {
        int[][] arr = new int[][]{{3, 3}, {5,-1}, {-2,4}};
        Solution973 solution973 = new Solution973();
        int[][] ints = solution973.kClosest(arr, 2);
        for (int[] anInt : ints) {
            System.out.println(Arrays.toString(anInt));
        }
    }
}

class Solution973 {
    public int[][] kClosest(int[][] points, int K) {
        /**
         * 无界优先队列，最小堆，默认指定值最小排在前面
         * 这里改造成最大堆，让 最大值保持在最前面
         */
        PriorityQueue<int[]> pq = new PriorityQueue<>((m, n) -> n[0] - m[0]);
        for (int i = 0; i < K; ++i) {
            pq.offer(new int[]{points[i][0] * points[i][0] + points[i][1] * points[i][1], i});
        }
        int n = points.length;
        for (int i = K; i < n; ++i) {
            int dist = points[i][0] * points[i][0] + points[i][1] * points[i][1];
            //pq[0]
            if (dist < pq.peek()[0]) {
                pq.poll();
                pq.offer(new int[]{dist, i});
            }
        }
        int[][] ans = new int[K][2];
        for (int i = 0; i < K; ++i) {
            // ponits[x]
            ans[i] = points[pq.poll()[1]];
        }
        return ans;

    }
}
