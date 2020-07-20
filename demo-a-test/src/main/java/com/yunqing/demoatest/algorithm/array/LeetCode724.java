package com.yunqing.demoatest.algorithm.array;

/**
 * @Description 寻找数组的中心索引
 * @Author yunqing
 * @Data 2020/7/21 6:05
 */
public class LeetCode724 {
    public static void main(String[] args) {
        int[] arr = new int[]{-1,-1,1,1,2};
        Solution724 s = new Solution724();
        System.out.println(s.pivotIndex(arr));
    }
}

class Solution724 {
    public int pivotIndex(int[] nums) {
        if (nums.length < 3) return -1;
        int[] arr = new int[10000];
        int numsSum = 0;
        for (int i = 0; i < nums.length; i++) {
            numsSum += nums[i];
        }
        numsSum -= nums[0];
        if (numsSum == 0) return 0;
        int arrSum = 0;
        for (int i = 1; i <= nums.length -1; i++) {
            arr[i - 1] = nums[i - 1];
            arrSum += arr[i - 1];
            numsSum -= nums[i];
            if (numsSum == arrSum) {
                return i;
            }
        }
        return -1;
    }
}