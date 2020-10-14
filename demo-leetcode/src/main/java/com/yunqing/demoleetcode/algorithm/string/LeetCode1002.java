package com.yunqing.demoleetcode.algorithm.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * LeetCode1002 查找常用字符串
 * @author yunqing
 * @since 2020/10/14 7:21
 */
public class LeetCode1002 {
    public static void main(String[] args) {
        Solution1002 solution1002 = new Solution1002();
        String[] A = new String[]{"bella","label","roller"};
        System.out.println(solution1002.commonChars(A));
    }
}

class Solution1002 {
    public List<String> commonChars(String[] A) {
        int[] minfreq = new int[26];
        Arrays.fill(minfreq, Integer.MAX_VALUE);
        for (String word: A) {
            int[] freq = new int[26];
            int length = word.length();
            //遍历数组中每一个字符，freq中记录其出现的次数
            for (int i = 0; i < length; ++i) {
                char ch = word.charAt(i);
                ++freq[ch - 'a'];
            }
            //同步到minfreq中，记录最少出现的次数
            for (int i = 0; i < 26; ++i) {
                minfreq[i] = Math.min(minfreq[i], freq[i]);
            }
        }

        List<String> ans = new ArrayList<>();
        //按照最少出席次数取相应字符
        for (int i = 0; i < 26; ++i) {
            for (int j = 0; j < minfreq[i]; ++j) {
                ans.add(String.valueOf((char) (i + 'a')));
            }
        }
        return ans;
    }
}
