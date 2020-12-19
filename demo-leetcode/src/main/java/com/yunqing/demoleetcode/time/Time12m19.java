package com.yunqing.demoleetcode.time;/**
 * @author yunqing
 * @since 2020/12/19 8:54
 */
public class Time12m19 {
    public static void main(String[] args) {

    }
}

class Solution12m19 {
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        // 先水平翻转,前一半的行
        for (int i = 0; i < n / 2; i++) {
            for (int j = 0; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n - i - 1][j];
                matrix[n - i - 1][j] = temp;
            }
        }
        // 再主对角线翻转
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }
}