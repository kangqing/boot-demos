package com.yunqing.demoleetcode.time;

import java.util.ArrayList;
import java.util.List;

/**
 * 12.6杨辉三角
 * @author yunqing
 * @since 2020/12/6 10:14
 */
public class Time12m6 {
    public static void main(String[] args) {
        Solution12m6 s = new Solution12m6();
        System.out.println(s.generate(5));
    }
}

class Solution12m6 {
    public List<List<Integer>> generate(int numRows) {
        // 存储返回结果
        List<List<Integer>> res = new ArrayList<>();
        // 存储上一行结果
        List<Integer> record = new ArrayList<>();
        // 循环一共多少行
        for (int i = 1; i <= numRows; i++) {
            // 构造当前行集合
            List<Integer> list = new ArrayList<>();
            for (int j = 0; j < i; j++) {
                if (j == 0 || j == i - 1) {
                    list.add(1);
                } else {
                    // 从保存的上一行数据取值计算
                    list.add(record.get(j) + record.get(j - 1));
                }
            }
            // 把构造好的当前行加入返回结果集
            res.add(list);
            // 记录当前行到record,以便下一行从record取值计算
            record = list;
        }
        return res;
    }
}
