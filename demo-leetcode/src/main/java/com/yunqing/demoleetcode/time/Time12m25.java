package com.yunqing.demoleetcode.time;

import java.util.Arrays;

/**
 * 12.25, 排序 + 贪心
 * @author yx
 * @since 2020/12/25 9:21
 */
public class Time12m25 {
    public static void main(String[] args) {

    }
}

class Solution12m25 {
    /**
     *
     * @param g 孩子胃口
     * @param s 饼干大小
     * @return
     */
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int gLen = g.length;
        int sLen = s.length;
        int resCount = 0;
        for (int i = 0, j = 0; i < gLen && j < sLen; i++, j++) {
            // 如果j < 饼干数量 && 孩子胃口 > 饼干大小
            while (j < sLen && g[i] > s[j]) {
                // 需要选择更大的饼干满足孩子的胃口
                j++;
            }
            if (j < sLen) resCount++;
        }
        return resCount;
    }
}
