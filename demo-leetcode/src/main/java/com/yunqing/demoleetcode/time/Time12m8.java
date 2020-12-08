package com.yunqing.demoleetcode.time;

import java.util.ArrayList;
import java.util.List;

/**
 * 12.8 回溯 + 剪枝
 * @author yx
 * @since 2020/12/8 17:46
 */
public class Time12m8 {
    public static void main(String[] args) {

    }
}

class Solution {
    public List<Integer> splitIntoFibonacci(String S) {
        List<Integer> res = new ArrayList<>();
        return res;
    }

    /**
     * 回溯
     * @param res 返回列表
     * @param s 字符串
     * @param index 索引
     * @param sum 前两个数的和
     * @param prev 上一个数
     * @return
     */
    private Boolean back(List<Integer> res, String s, int index, int sum, int prev) {
        // 结束条件，如果遍历结束，并且res中的数 >= 3个，这说明找到了一个
        if (index == s.length()) {
            return res.size() >= 3;
        }
        long currLong = 0;

        for (int i = index; i < s.length(); i++) {
            // 拆分出的数如果不是0则不能以0开头
            if (i > index && s.charAt(index) == '0') {
                break;
            }
            // 转化成十进制
            currLong = currLong * 10 + s.charAt(i) - '0';
            if (currLong > Integer.MAX_VALUE) {
                break;
            }
            int curr = (int) currLong;
            if (res.size() >= 2) {
                if (curr < sum) {
                    continue;
                } else if (curr > sum) {
                    break;
                }
            }
            res.add(curr);
            if (back(res, s, i + 1, prev + curr, curr)) {
                return true;
            } else {
                res.remove(res.size() - 1);
            }
        }
        return false;
    }
}
