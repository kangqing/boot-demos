package com.yunqing.demoleetcode.algorithm.array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @Description LeetCode面试题01.08 零矩阵
 * @Author yunqing
 * @Data 2020/7/28 6:13
 */
public class LeetCodeMST01_08 {
    public static void main(String[] args) {
        int[][] arr = new int[][]{{0,1,2,0}, {3,4,5,2}, {1,3,1,5}};
        SolutionMST01_08 s = new SolutionMST01_08();
        s.setZeroes(arr);
        for (int[] ints : arr) {
            System.out.println(Arrays.toString(ints));
        }
    }
}

class SolutionMST01_08 {
    public void setZeroes(int[][] matrix) {
        int m = matrix.length; //行
        int n = matrix[0].length; //列
        Set<Integer> rows = new HashSet<>();
        Set<Integer> col = new HashSet<>();
        //记录下需要清零的行和列
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    rows.add(i);
                    col.add(j);
                }
            }
        }
        //行清零
        for (Integer row : rows) {
            for (int i = 0; i < n; i++) {
                matrix[row][i] = 0;
            }
        }
        //列清零
        for (Integer integer : col) {
            for (int i = 0; i < m; i++) {
                matrix[i][integer] = 0;
            }
        }
    }
}
