package com.yunqing.demoleetcode.algorithm.array;

/**
 * LeetCode35 搜索插入位置
 * @Author yunqing
 * @Date 2020/7/21 15:57
 */
public class LeetCode35 {
    public static void main(String[] args) {
        int[] arr = new int[]{1,3,5,6};
        Solution35 s = new Solution35();
        int target = 0;
        System.out.println(s.searchInsert(arr, target));
        System.out.println(s.searchInsert_1(arr, target));
        System.out.println(s.searchInsert_2(arr, target));
    }
}

class Solution35 {

    /**
     * 时间复杂度 O(n)
     */
    int searchInsert(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < target) {
                continue;
            }
            return i;
        }
        return nums.length;
    }

    /**
     * 二分法
     * 时间复杂度 O(log n)
     */
    int searchInsert_1(int[] nums, int target) {
        if (nums == null) {
            return 0;
        }
        int low = 0;
        int high = nums.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                low = mid + 1;
            } else {
                if (mid > 0 && nums[mid - 1] < target) {
                    return mid;
                }else {
                    high = mid - 1;
                }
            }
        }

        return low;
    }


    /**
     * 二分法
     * 时间复杂度 O(log n)
     * 题解给的答案
     */
    int searchInsert_2(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;
        int ans = nums.length;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (nums[mid] < target) {
                low = mid + 1;
            } else {
                ans = mid;
                high = mid - 1;
            }
        }

        return ans;
    }
}


