package com.yunqing.demoatest.algorithm.binarysearch;

/**
 * LeetCode154 寻找旋转排序数组中的最小值 II
 * @author kangqing
 * @date 2020/8/20 16:28
 */
public class LeetCode154 {
    public static void main(String[] args) {
        int[] arr = new int[] {10, 1, 10, 10, 10, 10};
        Solution154 solution154 = new Solution154();
        System.out.println(solution154.query(arr));
    }
}

class Solution154 {
    int query(int[] arr) {
        int low = 0;
        int high = arr.length - 1;
        // high最后一个位置的元素是个特殊值
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] < arr[high]) {
                // 说明mid在mid - high之间
                high = mid;
            } else if (arr[mid] > arr[high]) {
                // 说明mid在low - min之间
                low = mid + 1;
            } else {
                //相等
                high--;
            }
        }
        return arr[low];
    }
}
