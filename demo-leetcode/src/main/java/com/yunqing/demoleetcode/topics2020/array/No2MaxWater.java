package com.yunqing.demoleetcode.topics2020.array;

/**
 * 双指针
 * 盛水最多的容器
 * @author kangqing
 * @since 2021/2/4 21:14
 */
public class No2MaxWater {
    public static void main(String[] args) {

    }
}

class SolutionNo2 {
    public int maxArea(int[] height) {
        int res = -1;
        int le = 0;
        int ri = height.length - 1;
        int lValue = height[0];
        int rValue = height[ri];
        while (le < ri) {
            // 算两个墙之前盛水最多
            res = Math.max(res, Math.min(height[le], height[ri]) * (ri - le));
            if (height[le] < height[ri]) {
                le++;
                while (le < ri && height[le] <= lValue) {
                    le++;
                }
                lValue = height[le];
            } else {
                ri--;
                while (le < ri && height[ri] <= rValue) {
                    ri--;
                }
                rValue = height[ri];
            }
        }
        return res;
    }
}
