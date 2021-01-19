package com.yunqing.demoleetcode.time;

import java.util.Arrays;

/**
 * 12月1号，考点：二分查找，注意点while(条件的left <= right是否带等号)
 * @author kangqing
 * @since 2020/12/1 17:33
 */
public class Time12m1 {
    public static void main(String[] args) {
        Solution12m1 s = new Solution12m1();
        int[] a = {1};
        int[] ints = s.searchRange(a, 1);
        System.out.println(Arrays.toString(ints));
    }
}

class Solution12m1 {
    public int[] searchRange(int[] nums, int target) {
        if(nums == null) return new int[]{-1, -1};
        //if(nums.length == 1 && nums[0] == target) return new int[]{0,0};

        int left = 0;
        int right = nums.length - 1;
        int index = -1;
        while(left <= right) {
            int mid = (right + left) / 2;
            if(nums[mid] == target) {
                index = mid;
                break;
            } else if (target > nums[mid]) {
                left = ++mid;
            } else {
                right = --mid;
            }
        }

        if(index == -1) return new int[]{-1, -1};
        for(int i = index; i>=0; i--) {
            if(nums[i] != target){
                left = ++i;
                break;
            }
        }
        for(int i = index; i<nums.length; i++) {
            if(nums[i] != target){
                right = --i;
                break;
            }
        }
        return new int[]{left, right};
    }

}
