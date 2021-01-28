package com.yunqing.demoleetcode.topics2020;

import java.util.Arrays;

/**
 * @author yx
 * @since 2021/1/28 14:17
 */
public class No2MergeSortArr {
    public static void main(String[] args) {
        int[] a1 = {0, 0, 0};
        int[] a2 = {2,5,6};
        final SolutionNo2 solutionNo2 = new SolutionNo2();
        //solutionNo2.merge_2(a1, 0, a2, 3);
        solutionNo2.merge_1(a1, 0, a2, 3);
        System.out.println(Arrays.toString(a1));
    }
}

class SolutionNo2 {

    public void merge_1(int[] nums1, int m, int[] nums2, int n) {
        // 额，亏我还知道用System.arraycopy()
        // 源数组， 从0开始， 复制到目标数组， 到目标数组 m 位置开始， 复制 n 位
        System.arraycopy(nums2, 0, nums1, m, n);
        Arrays.sort(nums1);
    }
    public void merge_2(int[] nums1, int m, int[] nums2, int n) {
        if(m == 0) {
            System.arraycopy(nums2, 0, nums1, 0, n);
            return;
        }
        if (n == 0) return;
        // 双指针
        int left = 0;
        int right = 0;
        int index = 0;
        int[] arr = new int[m + n];
        while(left < m && right < n) {
            if(nums1[left] < nums2[right]) {
                arr[index++] = nums1[left++];
            } else {
                arr[index++] = nums2[right++];
            }
        }
        while (left >= m && right < n) {
            arr[index++] = nums2[right++];
        }
        while (left < m) {
            arr[index++] = nums1[left++];
        }
        System.arraycopy(arr, 0, nums1, 0, m + n);
    }
}
