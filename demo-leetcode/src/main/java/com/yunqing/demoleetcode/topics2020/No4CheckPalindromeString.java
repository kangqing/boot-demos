package com.yunqing.demoleetcode.topics2020;

import java.util.ArrayList;
import java.util.List;

/**
 * 验证回文串
 */
public class No4CheckPalindromeString {
    public static void main(String[] args) {
        SolutionNo4 solutionNo4 = new SolutionNo4();
        System.out.println(solutionNo4.isPalindrome("A man, a plan, a canal: Panama"));

    }
}

/**
 * 双指针
 */
class SolutionNo4 {
    public boolean isPalindrome(String s) {
        List<Integer> list = new ArrayList<>();
        for (int i = 97; i < 123; i++) {
            list.add(i);
        }
        for (int i = 48; i < 58; i++) {
            list.add(i);
        }
        char[] chars = s.toCharArray();
        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            int integer = chars[left];
            if (integer >= 65 && integer <= 90) integer += 32;
            if (!list.contains(integer)) {
                left++;
                continue;
            }
            int ri = chars[right];
            if (ri >= 65 && ri <= 90) ri += 32;
            if (!list.contains(ri)) {
                right--;
                continue;
            }
            if (integer != ri) return false;
            left++;
            right--;
        }

        return true;
    }
}

