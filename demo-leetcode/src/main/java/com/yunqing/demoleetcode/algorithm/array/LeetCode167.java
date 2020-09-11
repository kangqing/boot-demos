package com.yunqing.demoleetcode.algorithm.array;

/**
 * LeetCode167 两数之和 II - 输入有序数组
 * @author yunqing
 * @since 2020/8/8 8:36
 */
public class LeetCode167 {
    public static void main(String[] args) {
        Solution167 solution167 = new Solution167();
        int[] numbers = new int[]{1,2,3,4,5,6,7,8,9,10};
        int[] ints = solution167.twoSum(numbers, 18);
        for (int anInt : ints) {
            System.out.println(anInt);
        }

    }
}

//思路：双指针解法
class Solution167 {
    public int[] twoSum(int[] numbers, int target) {
        int head = 0;
        int tail = numbers.length - 1;
        while (head < tail) {
            int sum = numbers[head] + numbers[tail];
            if (sum == target) {
                return new int[]{head + 1, tail + 1};
            } else if (sum < target) {
                head++;
            } else {
                tail--;
            }
        }
        //不存在
        return new int[]{-1, -1};
    }
}
