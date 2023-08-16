package com.yunqing.demoleetcode.algorithm.string;

/**
 * @author kangqing
 * @since 2023/8/17 06:58
 */
public class LeetCode1281 {
    public static void main(String[] args) {
        final Solution1281 s = new Solution1281();
        System.out.println(s.subtractProductAndSum(234));
    }
}

class Solution1281 {
    public int subtractProductAndSum(int n) {
        int j = 1;
        int h = 0;
        String str = String.valueOf(n);
        for (int i = 0; i < str.length(); i++) {
            int curr = Integer.parseInt(String.valueOf(str.charAt(i)));
            j *= curr;
            h += curr;
        }
        return j - h;
    }
}
