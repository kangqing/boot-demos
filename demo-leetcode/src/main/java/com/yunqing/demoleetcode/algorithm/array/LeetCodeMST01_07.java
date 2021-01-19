package com.yunqing.demoleetcode.algorithm.array;

import java.util.Arrays;

/**
 * @Description LeetCode面试题01.07 旋转矩阵
 * @Author kangqing
 * @Data 2020/7/27 17:55
 */
public class LeetCodeMST01_07 {
    public static void main(String[] args) {
        int[][] matrix = new int[][]{{1,2,3},{4,5,6},{7,8,9}};
        SolutionMST01_07 s = new SolutionMST01_07();
        s.rotate(matrix);
        for (int[] ints : matrix) {
            System.out.println(Arrays.toString(ints));
        }
    }
}

class SolutionMST01_07 {
    public void rotate(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] arr = new int[n][m];
        int j = 0;
        for(int y=m; y>0; y--) {
            int x = m;
            for(int i = 0; i<n; i++) {
                arr[j][i] = matrix[x-1][j];
                x--;
            }
            j++;
        }
        //二维数组的拷贝方式
        for (int i = 0; i < arr.length; i++) {
            matrix[i] = arr[i].clone();
        }
    }
}
