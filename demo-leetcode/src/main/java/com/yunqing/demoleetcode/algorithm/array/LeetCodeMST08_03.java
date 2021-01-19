package com.yunqing.demoleetcode.algorithm.array;

/**
 * @author kangqing
 * @description LeetCodeMST08_03 魔术索引
 * @date 2020/7/31 12:23
 */
public class LeetCodeMST08_03 {
    public static void main(String[] args) {
        int[] arr = new int[]{1, 1, 1};
        SolutionMST08_03 s = new SolutionMST08_03();
        System.out.println(s.findMagicIndex(arr));
    }
}

class SolutionMST08_03 {
    int findMagicIndex(int[] nums) {
        int index = 0;
        for (int num : nums) {
            if (num == index) {
                return index;
            }
            index++;
        }
        return -1;
    }
}
