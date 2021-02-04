package com.yunqing.demoleetcode.topics2020.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 三数之和
 * @author kangqing
 * @since 2021/2/4 22:53
 */
public class No3ThreeSum {
    public static void main(String[] args) {
        var s = new SolutionNo3();
        System.out.println(s.threeSum(new int[] {0, 0, 0}));
    }
}
// 双指针
class SolutionNo3 {
    public List<List<Integer>> threeSum(int[] nums) {
        int n = nums.length - 1;
        List<List<Integer>> list = new ArrayList<>();
        if (n < 2) return list;
        // 先排序
        Arrays.sort(nums);
        // 先找第一个数, 后边留出两个数的量
        for (int i = 0; i < n - 1; i++) {
            // 最小的数都大于0所以没有满足的结果
            if (nums[0] > 0) break;
            // 等于上一个数，去除重复条件
            if (i > 0 &&nums[i] == nums[i - 1]) continue;
            // 剩下两个数应该 = target
            int target = -nums[i];
            int left = i + 1;
            int right = n;
            while (left < right) {
                if (nums[left] + nums[right] == target) {
                    list.add(List.of(nums[i], nums[left], nums[right]));
                    left++;
                    right--;
                    while (left < right && nums[left] == nums[left - 1]) {
                        // 等于上一个数，则再往后一位
                        left++;
                    }
                    while (left < right && nums[right] == nums[right + 1]) {
                        // 等于上一个数则在往前一位
                        right--;
                    }
                } else if (nums[left] + nums[right] < target) {
                    left++;
                } else {
                    right--;
                }
            }

        }
        return list;

    }
}
