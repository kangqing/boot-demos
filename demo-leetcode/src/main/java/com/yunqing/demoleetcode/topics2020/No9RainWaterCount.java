package com.yunqing.demoleetcode.topics2020;

/**
 * 接雨水
 * @author kangqing
 * @since 2021/2/2 9:26
 */
public class No9RainWaterCount {
    public static void main(String[] args) {
        final SolutionNo9 solutionNo9 = new SolutionNo9();
        System.out.println(solutionNo9.trap(new int[] {4,2,0,3,2,5}));
    }
}
// 双指针
class SolutionNo9 {
    public int trap(int[] height) {
        int count = 0;
        int le = 0;
        int ri = height.length - 1;
        int leMax = 0;
        int riMax = 0;
        // 如果左指针 < 右指针
        while (le < ri) {
            // 左当前值小于右当前值，则以左墙低，以左为基准盛雨水
            if (height[le] < height[ri]) {
                // 左当前值大于等于 做最大值
                if (height[le] >= leMax) {
                    leMax = height[le++];
                } else {
                    // 左当前值 < 最大值得时候能有雨水，也就是左边有比当前值高的墙
                    count += leMax - height[le++];
                }
            } else {
                // 左当前值更大，则以右墙为基准盛雨水
                if (height[ri] >= riMax) {
                    riMax = height[ri--];
                } else {
                    // 右当前值 < 右最大值，证明右面有比当前值更大的墙，所以才能盛雨水
                    count += riMax - height[ri--];
                }
            }
        }
        return count;
    }
}
