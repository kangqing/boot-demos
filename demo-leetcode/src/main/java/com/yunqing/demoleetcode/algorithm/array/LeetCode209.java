package com.yunqing.demoleetcode.algorithm.array;

/**
 * LeetCode209 长度最小的连续子数组
 * @author yunqing
 * @since 2020/8/10 6:41
 */
public class LeetCode209 {
    public static void main(String[] args) {
        Solution209 solution209 = new Solution209();
        int[] arr = new int[]{2,3,1,2,4,3};
        System.out.println(solution209.minSubArrayLen_1(7, arr));
    }
}

class Solution209 {
    /**
     * TODO 参考答案
     * 双指针解法，时间复杂度O(n)
     * @param s
     * @param nums
     * @return
     */
    public int minSubArrayLen_1(int s, int[] nums) {
        int len = nums.length;
        if (len == 0) {
            return 0;
        }
        int fast = 0;
        int slow = 0;
        int sum = 0;
        int min = Integer.MAX_VALUE;
        while(fast < len) {
            sum += nums[fast];
            while (sum >= s) {
                min = Math.min(min, fast - slow + 1);
                sum -= nums[slow];
                slow++;
            }
            fast++;
        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }

    /**
     * TODO 还没完成二分查找法
     * 二分查找法，时间复杂度O(log n)
     * @param s
     * @param nums
     * @return
     */
    public int minSubArrayLen_2(int s, int[] nums) {

        return 0;
    }
}
