package com.yunqing.demoatest.algorithm.binarysearch;

/**
 * LeetCode744 寻找比目标字母大的最小字母
 * @author yx
 * @date 2020/8/20 9:59
 */
public class LeetCode744 {
    public static void main(String[] args) {
        Solution744 solution744 = new Solution744();
        char[] arr = new char[]{'b', 'c', 'n'};
        char target = 'v';
        System.out.println(solution744.nextGreatestLetter(arr, target));
    }

}

class Solution744 {
    public char nextGreatestLetter(char[] letters, char target) {
        int lo = 0, hi = letters.length;
        // 思路是找到target应该插入的位置
        while (lo < hi) {
            int mi = lo + (hi - lo) / 2;
            if (letters[mi] <= target) lo = mi + 1;
            else hi = mi;
        }
        // 巧妙的运用取模运算获取目标结果
        return letters[lo % letters.length];
    }
}
