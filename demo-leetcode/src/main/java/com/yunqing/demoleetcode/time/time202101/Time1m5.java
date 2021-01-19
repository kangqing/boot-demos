package com.yunqing.demoleetcode.time.time202101;

import java.util.ArrayList;
import java.util.List;

/**
 * 1.5 双指针
 * @author kangqing
 * @since 2021/1/5 10:11
 */
public class Time1m5 {
    public static void main(String[] args) {
        final Solution1m5 s = new Solution1m5();
        System.out.println(s.largeGroupPositions("aaa"));
    }
}

/**
 * 双指针
 */
class Solution1m5 {
    public List<List<Integer>> largeGroupPositions(String s) {
        List<List<Integer>> res = new ArrayList<>();
        if (s.length() < 3) return  res;
        int slow = 0;
        int fast = 1;
        int count = 1;
        while (fast < s.length()) {
            if (count >= 3) {
                if (s.charAt(fast) != s.charAt(slow)) {
                    List<Integer> list = new ArrayList<>();
                    list.add(slow);
                    list.add(fast - 1);
                    res.add(list);
                    count = 1;
                    slow = fast++;
                } else {
                    fast++;
                    count++;
                }
            } else {
                if (s.charAt(fast) != s.charAt(slow)) {
                    slow = fast++;
                    count = 1;
                } else {
                    fast++;
                    count++;
                }
            }
        }
        if (count >= 3) {
            List<Integer> list = new ArrayList<>();
            list.add(slow);
            list.add(fast - 1);
            res.add(list);
        }
        return res;
    }
}
