package com.yunqing.demoleetcode.time;

import java.util.Arrays;

/**
 * 12.24 贪心，这道题目一定是要确定一边之后，再确定另一边，例如比较每一个孩子的左边，然后再比较右边，如果两边一起考虑一定会顾此失彼。
 * @author yx
 * @since 2020/12/24 9:37
 */
public class Time12m24 {
    public static void main(String[] args) {
        final Solution12m24 s = new Solution12m24();
        System.out.println(s.candy(new int[]{1,2,2}));

    }
}

class Solution12m24 {
    public int candy(int[] ratings) {
        int len = ratings.length;
        int[] count = new int[len];
        Arrays.fill(count, 1);
        // 从前向后遍历，后面大的数 + 1
        for (int i = 1; i < len; i++) {
            if (ratings[i] > ratings[i - 1]) {
                count[i] = count[i - 1] + 1;
            }
        }
        // 从后向前遍历，前面大的数,判断后面的数 + 1和前面的数比较取最大值
        for (int i = len - 2; i >= 0 ; i--) {
            if (ratings[i] > ratings[i + 1]) {
                count[i] = Math.max(count[i], count[i + 1] + 1);
            }
        }
        return Arrays.stream(count).sum();

    }
}