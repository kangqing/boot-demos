package com.yunqing.demoatest.algorithm.array;

/**
 * @Description LeetCode739 每日温度
 * @Author yunqing
 * @Data 2020/7/18 6:41
 */
public class LeetCode739 {
    public static void main(String[] args) {
        int[] T = new int[]{55,38,53,81,61,93,97,32,43,78};
        Solution739 s = new Solution739();
        for (int i : s.dailyTemperatures(T)) {
            System.out.println(i);
        }
    }
}

/**
 * 思路：双指针，一个指针指向数组当前索引，另一个指向索引之后元素
 */
class Solution739 {
    public int[] dailyTemperatures(int[] T) {
        //定义返回数组
        int[] result = new int[T.length];
        int len = T.length;
        for (int i = 0; i < len; i++) {
            int target = i + 1;
            if (target == len) {
                result[i] = 0;
                return result;
            }
            int daily = 1;
            while (true) {
                if (target == len) {
                    result[i] = 0;
                    break;
                }
                if (T[i] < T[target]) {
                    result[i] = daily;
                    break;
                }
                daily++;
                target++;
            }
        }
        return result;
    }
}
