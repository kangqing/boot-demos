package com.yunqing.demoleetcode.time;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author yx
 * @since 2020/12/11 10:01
 */
public class Time12m11 {
    public static void main(String[] args) {
        Solution12m11 s = new Solution12m11();
        System.out.println(s.predictPartyVictory("RDD"));
    }
}
class Solution12m11 {
    public String predictPartyVictory(String senate) {
        int n = senate.length();
        Deque<Integer> rQueue = new ArrayDeque<>();
        Deque<Integer> dQueue = new ArrayDeque<>();
        // 把当前政党分成两个队列，索引值加入队列，值越小越先投票
        for (int i = 0; i < n; i++) {
            if (senate.charAt(i) == 'R') {
                rQueue.offer(i);
            }else {
                dQueue.offer(i);
            }
        }
        while (!rQueue.isEmpty() && !dQueue.isEmpty()) {
            int r = rQueue.poll();
            int d = dQueue.poll();
            if (r < d) {
                // r团队有优先投票权，且下一轮扔能投票
                rQueue.offer(r + n);
            } else {
                dQueue.offer(d + n);
            }
        }

        return rQueue.isEmpty() ? "Dire" : "Radiant";
    }
}