package com.yunqing.demoleetcode.offer;

/**
 * 剑指 Offer 04. 二维数组中的查找
 * @author yx
 * @since 2020/12/24 11:16
 */
public class Offer04 {
    public static void main(String[] args) {
    }
}

class Solution {
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        if (matrix == null) return false;
        int rows = matrix.length;
        int cols = matrix[0].length;
        // 从右上角开始线性查找，如果 == 则返回， 如果 < target 则列 - 1， 否则 行 - 1
        int row = 0;
        int col = cols - 1;
        while (col >= 0 && row < rows) {
            int curr = matrix[row][col];
            if (curr == target) return true;
            else if (curr < target) col--;
            else row++;
        }
        return false;
    }
}
