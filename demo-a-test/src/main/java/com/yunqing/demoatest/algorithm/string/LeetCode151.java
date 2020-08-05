package com.yunqing.demoatest.algorithm.string;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yunqing
 * @description LeetCode151 翻转字符串里的单词
 * @date 2020/8/5 20:31
 */
public class LeetCode151 {
    public static void main(String[] args) {
        String s = " sit  a down up  good   ";
        Solution151 solution151 = new Solution151();
        System.out.println(solution151.reverseWords(s));
    }
}

class Solution151 {
    public String reverseWords(String s) {
        String[] arr = s.split(" ");
        List<String> collect = Arrays.stream(arr).filter(e -> !StringUtils.isBlank(e)).collect(Collectors.toList());
        //实现集合元素的翻转
        Collections.reverse(collect);
        //用空格拼接集合中的元素
        return String.join(" ", collect);
    }
}
