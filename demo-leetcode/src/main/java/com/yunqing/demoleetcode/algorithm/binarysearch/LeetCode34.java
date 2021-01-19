package com.yunqing.demoleetcode.algorithm.binarysearch;

import java.util.Arrays;

/**
 * LeetCode34 在排序数组中查找元素的第一个和最后一个位置
 * @author kangqing
 * @date 2020/8/18 10:00
 */
public class LeetCode34 {
    public static void main(String[] args) {
        Solution34 s = new Solution34();
        var arr = new int[]{1, 2, 3, 4, 8, 8, 8, 8, 8, 8, 8, 10};
        System.out.println(Arrays.toString(s.searchRange(arr, 8)));
    }
}

class Solution34 {
    public int[] searchRange(int[] nums, int target) {
        int[] res = new int[]{-1, -1};
        if (nums == null) {
            return res;
        }
        res[0] = searchTargetLeftOrRight(true, nums, target);
        res[1] = searchTargetLeftOrRight(false, nums, target);
        return res;
    }

    private int searchTargetLeftOrRight(boolean b, int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] < target) {
                low = mid + 1;
            } else if (nums[mid] > target) {
                high = mid - 1;
            } else {
                if (b) {
                    //查找最左target
                    if (mid > 0 && nums[mid - 1] == target) {
                        high = mid - 1;
                    } else {
                        return mid;
                    }
                }else {
                    //查找最右target
                    if (mid < nums.length - 1 && nums[mid + 1] == target) {
                        low = mid + 1;
                    }else {
                        return mid;
                    }
                }
            }

        }
        return -1;
    }
}
