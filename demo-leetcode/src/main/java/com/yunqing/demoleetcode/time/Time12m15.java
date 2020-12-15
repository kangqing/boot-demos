package com.yunqing.demoleetcode.time;

/**
 * 12.15 贪心，一位一位来求解
 * @author yunqing
 * @since 2020/12/15 21:36
 */
public class Time12m15 {
    public static void main(String[] args) {
        Solution12m15 s = new Solution12m15();
        System.out.println(s.monotoneIncreasingDigits(0));
    }
}

// 1234321     2333332
// 1233999     2299999
class Solution12m15 {
    public int monotoneIncreasingDigits(int N) {
        // 拆分成数组
        char[] chars = String.valueOf(N).toCharArray();
        int n = chars.length;
        int i = 1;
        // 找出第一个后边小于前边数的索引
        while(i < n && chars[i] >= chars[i - 1]) {
            i++;
        }
        if (i == n) return N;
        // 否则必定i < n
        while (i > 0 && chars[i] < chars[i - 1]) {
            // 2333332 如此数一直找到  （当前索引减一之后 >= 上一位） 的索引
            chars[--i] -= 1;
        }
        // 后面补9即单调递增最大数
        for (i += 1; i < n; i++) {
            chars[i] = '9';
        }
        return Integer.parseInt(new String(chars));
    }
}
