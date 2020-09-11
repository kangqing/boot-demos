package com.yunqing.demoleetcode.algorithm.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @Description LeetCode56 合并区间
 * @Author yx
 * @Data 2020/7/27 16:58
 */
public class LeetCode56 {
    public static void main(String[] args) {
        int[][] intervals = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        Solution56 solution56 = new Solution56();
        int[][] arr = solution56.merge(intervals);
        for (int[] ints : arr) {
            System.out.println(Arrays.toString(ints));
        }
    }
}

/**
 * 合并区间，思路先排序
 */
class Solution56 {
    public int[][] merge(int[][] intervals) {
        if (intervals.length < 2) {
            return intervals;
        }
        //按照数组的首位排序，确保能够合并的数组是连续的
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        List<int[]> list = new ArrayList<>();
        list.add(intervals[0]);
        //第一个数组已经放进list,使用第二个开始，依次比较，不能合并直接add,能合并则修改list中最后一个数组的arr[1]
        for (int i = 1; i < intervals.length; i++) {
            int[] arr = list.get(list.size() - 1);
            int[] curr = intervals[i];
            if (arr[1] < curr[0]) {
                //不能合并，直接添加当前数组到list
                list.add(curr);
            } else {
                //能合并，修改list中数组结尾为最大的数
                arr[1] = Math.max(arr[1], curr[1]);
            }
        }
        return list.toArray(new int[list.size()][]);
    }
}
