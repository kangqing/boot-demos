package com.yunqing.demoatest.algorithm.string;

/**
 * 周赛 LeetCode5484 找出第 N 个二进制字符串中的第 K 位
 * @author yunqing
 * @since 2020/8/9 22:39
 */
public class LeetCode5484 {
    public static void main(String[] args) {
        Solution5484 solution5484 = new Solution5484();
        System.out.println(solution5484.findKthBit(4, 9));
    }
}
class Solution5484 {
    public char findKthBit(int n, int k) {
        String Si = getSi(n);
        return Si.charAt(k -1);
    }

    /**
     * 递归求解
     * @param i
     * @return
     */
    private String getSi(int i) {

        if (i > 1) {
            String Si_1 = getSi(i - 1);
            String s = Si_1 + "1" + reverse(invert(Si_1));
            return s;
        }
        return "0";
    }

    /**
     * 遍历获取二进制反值
     * @param str
     * @return
     */
    private String invert(String str) {
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '0') {
                chars[i] = '1';
            }else {
                chars[i] = '0';
            }
        }
        StringBuilder sb = new StringBuilder();
        for (char aChar : chars) {
            sb.append(aChar);
        }
        return sb.toString();
    }

    /**
     * 双指针反转字符串
     * @param str
     * @return
     */
    private String reverse(String str) {
        int head = 0;
        int tail = str.length() - 1;
        char[] chars = str.toCharArray();
        while (head < tail) {
            char t = chars[tail];
            chars[tail] = chars[head];
            chars[head] = t;
            head++;
            tail--;
        }
        StringBuilder sb = new StringBuilder();
        for (char aChar : chars) {
            sb.append(aChar);
        }
        return sb.toString();
    }
}
