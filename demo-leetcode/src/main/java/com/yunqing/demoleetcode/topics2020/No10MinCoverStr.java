package com.yunqing.demoleetcode.topics2020;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 最小覆盖子串
 * @author kangqing
 * @since 2021/2/2 21:17
 */
public class No10MinCoverStr {
    public static void main(String[] args) {
        SolutionNo10 solutionNo10 = new SolutionNo10();
        System.out.println(solutionNo10.minWindow("A", "AA"));
    }
}

// 双指针
class SolutionNo10 {
    public String minWindow(String s, String t) {
        // 存 t 中字符数量
        Map<Character, Integer> tMap = new HashMap<>();
        // 存 s 中字符数量
        Map<Character, Integer> sMap = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            // 统计 t 中的各个字符的数量
            tMap.put(t.charAt(i), tMap.getOrDefault(t.charAt(i), 0) + 1);
        }
        // 快慢指针
        int fast = -1;
        int slow = 0;
        // 截取返回结果用
        int begin = -1;
        int stop = -1;
        // 包含 t 的最小子串长度
        int count = Integer.MAX_VALUE;
        int len = s.length();
        while (fast < len) {
            ++fast;
            // t 中包含 s 当前遍历的字符
            if(fast < len && tMap.containsKey(s.charAt(fast))) {
                // 存当前字符到 sMap中
                sMap.put(s.charAt(fast), sMap.getOrDefault(s.charAt(fast), 0) + 1);
            }
            // 检查是否 t 中所有字符都在 s 的子串中
            while (slow <= fast && check(sMap, tMap)) {
                // 如果子串的长度减小，则更新截取开始、结束的指针
                if (fast - slow + 1 < count) {
                    count = fast - slow + 1;
                    begin = slow;
                    stop = slow + count;
                }
                // 慢指针向前一位，如果存在 t 中，则减少此字符的数量
                if (tMap.containsKey(s.charAt(slow))) {
                    sMap.put(s.charAt(slow), sMap.getOrDefault(s.charAt(slow), 0) - 1);
                }
                ++slow;
            }
        }
        // 返回包含 t 的最小子串
        return begin == -1 ? "" : s.substring(begin, stop);
    }

    /**
     * 检查 t 中的字符是否全在 s子串中
     * @return
     */
    private boolean check(Map<Character, Integer> sMap, Map<Character, Integer> tMap) {
        AtomicBoolean flag = new AtomicBoolean(true);
        tMap.forEach((k, v) -> {
            Integer count = sMap.getOrDefault(k, 0);
            if (count < v) flag.set(false);
        });
        return flag.get();
    }



}