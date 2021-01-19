package com.yunqing.demoleetcode.algorithm.string;

import java.util.Arrays;

/**
 * @Description 字符串不可变测试
 * @Author kangqing
 * @Data 2020/7/29 16:10
 */
public class StringTest {
    public static void main(String[] args) {
        String s = "Hello World";
        //s[5] = ',';
        char[] chars = s.toCharArray();
        chars[5] = ',';
        System.out.println(Arrays.toString(chars));
        StringBuilder sb = new StringBuilder();
        for (char aChar : chars) {
            sb.append(aChar);
        }
        System.out.println(sb);
        System.out.println(sb.substring(0, 3));
    }
}
