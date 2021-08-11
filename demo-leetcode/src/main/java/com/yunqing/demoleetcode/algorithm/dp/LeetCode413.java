package com.yunqing.demoleetcode.algorithm.dp;

import java.util.Arrays;

/**
 * 等差数列划分
 * @author kangqing
 * @since 2021/8/10 21:02
 */
public class LeetCode413 {
    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 4, 4, 4};
        var s = new Solution413();
        System.out.println("双指针解法：" + s.numberOfArithmeticSlices(arr));
        System.out.println("递归解法：" + s.number2(arr));
        System.out.println("动态规划解法：" + s.number3(arr));
    }
}

class Solution413 {
    /**
     * 移动滑块，双指针
     * @param nums
     * @return
     */
    public int numberOfArithmeticSlices(int[] nums) {
        int n = nums.length;
        if (n < 3) return 0;
        int ans = 0;
        for (int i = 0; i < n - 2; i++) {
            // 起点开始,3个长度子数组，4个长度子数组...一直到不再满足等差条件，换下一个起点
            int d = nums[i + 1] - nums[i];
            for (int j = i + 1; j < n - 1; j++) {
                if (nums[j + 1] - nums[j] == d)
                    ans++;
                else break;
            }
        }
        return ans;
    }

    /**
     * 递归方法：
     */
    int ans = 0;
    public int number2(int[] nums) {
        int n = nums.length;
        digui(nums, n - 1);
        return ans;
    }

    private int digui(int[] A, int end) {
        if (end < 2) return 0;
        int op = 0;
        if (A[end] - A[end - 1] == A[end - 1] - A[end - 2]) {
            op = 1 + digui(A, end - 1);
            ans += op;
        } else {
            digui(A, end - 1);
        }
        return op;
    }

    /**
     * 动态规划
     * @param nums
     * @return
     */
    public int number3(int[] nums) {
        int n = nums.length;
        if (n < 3) return 0;
        int[] dp = new int[n];
        for (int i = 1; i < n - 1; i++) {
            if (nums[i - 1] + nums[i + 1] == 2 * nums[i]) {
                dp[i] = 1 + dp[i - 1];
            }
        }
        return Arrays.stream(dp).sum();
    }
}
