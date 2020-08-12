package com.yunqing.demoatest.algorithm.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @Description LeetCode498 对角线遍历
 * @Author yunqing
 * @Data 2020/7/29 6:09
 */
public class LeetCode498 {
    public static void main(String[] args) {
        int[][] arr = new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
        int[][] arr1 = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int[][] arr2 = new int[][]{{1, 2, 3, 4, 5}, {6, 7, 8, 9, 10}, {11, 12, 13, 14, 15}};
        int[][] arr3 = new int[][]{{1, 2, 3, 4, 5}, {6, 7, 8, 9, 10}};
        int[][] arr4 = new int[][]{{1, 2}, {3, 4}};
        Solution498 s = new Solution498();
        System.out.println(Arrays.toString(s.findDiagonalOrder(arr)));
        System.out.println(Arrays.toString(s.findDiagonalOrder(arr1)));
        System.out.println(Arrays.toString(s.findDiagonalOrder(arr2)));
        System.out.println(Arrays.toString(s.findDiagonalOrder(arr3)));
        System.out.println(Arrays.toString(s.findDiagonalOrder(arr4)));
    }
}

class Solution498 {
    public int[] findDiagonalOrder(int[][] matrix) {
        //验空
        if (matrix == null || matrix.length == 0) {
            return new int[0];
        }
        
        int row = matrix.length; //行
        int col = matrix[0].length; //列
        
        int listCount = row + col - 1;

        int[] result = new int[row * col];
        int index = 0;

        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < listCount; i++) {
            list.clear();
            //计算数组行索引
            int m = i < col ? 0 : i - col + 1;
            //计算数组列索引
            int n = i < col ? i : col - 1;

            while (m < row && n > -1) {
                list.add(matrix[m][n]);
                ++m;
                --n;
            }

            if (i % 2 == 0) {
                Collections.reverse(list);
            }

            for (int j = 0; j < list.size(); j++) {
                result[index++] = list.get(j);
            }

        }

        return result;
        
        
    }


}
