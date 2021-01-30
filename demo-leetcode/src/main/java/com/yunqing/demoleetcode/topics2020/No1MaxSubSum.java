package com.yunqing.demoleetcode.topics2020;

import java.util.Arrays;

/**
 * 最大子序和
 * @author kangqing
 * @since 2021/1/28 11:10
 */
public class No1MaxSubSum {
    public static void main(String[] args) {
        int[] arr = {-2,1,-3,4,-1,2,1,-5,4};
        // 最大子序{4,-1,2,1} = 6
        final SolutionNo1 solutionNo1 = new SolutionNo1();
        System.out.println(solutionNo1.maxSubArray_1(arr));

        System.out.println(solutionNo1.maxSubArray_2(arr));

    }
}

class SolutionNo1 {
    // 动态规划简化后
    public int maxSubArray_1(int[] nums) {
        // 当前数字
        int curr = nums[0];
        // 当前和
        int sum = nums[0];

        for (int i = 1; i < nums.length; i++) {
            // 扫描到当前数小于 0 则舍去， 令当前数 = 下一个数
            if (curr < 0) {
                curr = nums[i];
            }
            // 否则 大于等于 0 则对和有积极影响，则加上
            else {
                curr += nums[i];
            }
            // 如果 curr 更大则，sum = curr
            sum = Math.max(sum, curr);

        }
        return sum;
    }

    //-2,1,-3,4,-1,2,1,-5,4

    // 动态规划 转移方程： dp[i] = Math.max(dp[i - 1] + nums[i], nums[i])
    // 以当前数结尾为最大，有可能是当前数 + 前一个数结尾最大dp[i - 1]，或者是当前数就是最大（前一个最大小于0时候）
    public int maxSubArray_2(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
        }
        return Arrays.stream(dp).max().getAsInt();
    }
}
