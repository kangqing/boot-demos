package com.yunqing.demoleetcode.time;

import java.util.HashMap;
import java.util.Map;

/**
 * 贪心：两个哈希表，永远先把元素接在上一个元素为结尾的子序列后面，如果没有上一个元素结尾的子序列，再结合下两个元素创建新的3个元素子序列
 * @author kangqing
 * @since 2020/12/4 9:48
 */
public class Time12m4 {
    public static void main(String[] args) {

    }
}

class Solution12m4 {
    public boolean isPossible(int[] nums) {
        // 存储数组中每个数字的数量
        Map<Integer, Integer> map = new HashMap<>();
        // 存储以某个数为结尾的子序列的数量
        Map<Integer, Integer> endMap = new HashMap<>();
        // 统计每个数字的个数
        for (int num : nums) {
            Integer val = map.getOrDefault(num, 0) + 1;
            map.put(num, val);
        }
        // 遍历数组
        for (int num : nums) {
            // 获取当前遍历到的数字的数量
            Integer currCount = map.getOrDefault(num, 0);
            // 当前数字数量 > 0 , 优先考虑能不能加到一个子序列尾部，其次考虑创建一个3个元素的子序列
            if (currCount > 0) {
                // 上一个数字 num - 1 为结尾的子序列数量
                Integer count = endMap.getOrDefault(num - 1, 0);
                if (count > 0) {
                    // num - 1的子序列数量 - 1
                    endMap.put(num - 1, --count);
                    // 把 num 加到刚才的子序列之后， num 结尾的子序列 + 1
                    endMap.put(num, endMap.getOrDefault(num, 0) + 1);
                    // 存储每个数字数量的哈希表减去一个num的数量
                    map.put(num, --currCount);
                } else {
                    // 构造一个新的三个元素的子序列
                    Integer curr1 = map.getOrDefault(num + 1, 0);
                    Integer curr2 = map.getOrDefault(num + 2, 0);
                    if (curr1 > 0 && curr2 > 0) {
                        // 计数的哈希表，给这三个元素都减一
                        map.put(num, --currCount);
                        map.put(num + 1, --curr1);
                        map.put(num + 2, --curr2);
                        // 多出一个以 num + 2结尾的子序列
                        endMap.put(num + 2, endMap.getOrDefault(num + 2, 0) + 1);
                    } else {
                        return false;
                    }
                }
            }
        }
        return true;
        
    }
}
