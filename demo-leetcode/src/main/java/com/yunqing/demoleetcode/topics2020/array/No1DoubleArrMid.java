package com.yunqing.demoleetcode.topics2020.array;

import java.util.Arrays;

/**
 * 寻找两个正序数组的中位数
 * @author kangqing
 * @since 2021/2/2 23:03
 */
public class No1DoubleArrMid {
    public static void main(String[] args) {

    }
}

// 二分查找
class SolutionNo1 {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] arr = new int[nums1.length + nums2.length];
        // 两个数组合并成一个
        System.arraycopy(nums1, 0, arr, 0, nums1.length);
        System.arraycopy(nums2, 0, arr, nums1.length, nums2.length);
        Arrays.sort(arr);
        int low = 0;
        int high = arr.length - 1;
        // 找到中间位置
        int mid = (low + high) / 2;
        if (arr.length % 2 == 1) {
            return arr[mid];
        } else {
            return (double)(arr[mid] + arr[mid + 1]) / 2;
        }
    }
}