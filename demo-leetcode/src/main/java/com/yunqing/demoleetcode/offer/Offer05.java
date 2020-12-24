package com.yunqing.demoleetcode.offer;

import java.util.ArrayList;
import java.util.List;

/**
 * 剑指 Offer 05. 替换空格
 * @author yx
 * @since 2020/12/24 17:33
 */
public class Offer05 {
    public static void main(String[] args) {

    }
}

class SolutionOffer05 {
    public String replaceSpace(String s) {
        int len = s.length();
        List<String> list = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            if (' ' == s.charAt(i)) {
                list.add("%20");
            } else {
                list.add(String.valueOf(s.charAt(i)));
            }
        }
        return String.join("", list);

    }
}
