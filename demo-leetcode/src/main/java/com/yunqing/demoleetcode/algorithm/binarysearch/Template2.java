package com.yunqing.demoleetcode.algorithm.binarysearch;

/**
 * 二分查找 模板 II
 * @author yunqing
 * @since 2020/8/15 6:21
 */
public class Template2 {
    public static void main(String[] args) {
        var arr = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        var target = 6;
        var s = new SolutionTemplate2();
        System.out.println(s.binarySearch(arr, target));
    }
}

class SolutionTemplate2 {
    int binarySearch(int[] nums, int target){
        if(nums == null || nums.length == 0)
            return -1;

        int left = 0, right = nums.length;
        while(left < right){
            // Prevent (left + right) overflow
            int mid = left + (right - left) / 2;
            if(nums[mid] == target){ return mid; }
            else if(nums[mid] < target) { left = mid + 1; }
            else { right = mid; }
        }

        // Post-processing:
        // End Condition: left == right
        if(left != nums.length && nums[left] == target) return left;
        return -1;
    }
}
