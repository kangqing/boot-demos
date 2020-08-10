package com.yunqing.demoatest.algorithm.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * @Description LeetCode498 对角线遍历
 * @Author yunqing
 * @Data 2020/7/29 6:09
 */
public class LeetCode498 {
    public static void main(String[] args) {
        int[][] arr = new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
        Solution498 s = new Solution498();
        System.out.println(Arrays.toString(s.findDiagonalOrder(arr)));
        System.out.println(Arrays.toString(s.findDiagonalOrder_1(arr)));
    }
}
//TODO ----------- 自己没想出来，看题解的
class Solution498 {
    public int[] findDiagonalOrder(int[][] matrix) {
        // Check for empty matrices
        if (matrix == null || matrix.length == 0) {
            return new int[0];
        }

        // Variables to track the size of the matrix
        int row = matrix.length;
        int col = matrix[0].length;

        // The two arrays as explained in the algorithm
        int[] result = new int[row * col];
        int k = 0;
        ArrayList<Integer> intermediate = new ArrayList<Integer>();

        // We have to go over all the elements in the first
        // row and the last column to cover all possible diagonals
        for (int d = 0; d < row + col - 1; d++) {

            // Clear the intermediate array every time we start
            // to process another diagonal
            intermediate.clear();

            // We need to figure out the "head" of this diagonal
            // The elements in the first row and the last column
            // are the respective heads.
            int m = d < col ? 0 : d - col + 1;
            int n = d < col ? d : col - 1;

            // Iterate until one of the indices goes out of scope
            // Take note of the index math to go down the diagonal
            while (m < row && n > -1) {

                intermediate.add(matrix[m][n]);
                ++m;
                --n;
            }

            // Reverse even numbered diagonals. The
            // article says we have to reverse odd
            // numbered articles but here, the numbering
            // is starting from 0 :P
            if (d % 2 == 0) {
                Collections.reverse(intermediate);
            }

            for (int i = 0; i < intermediate.size(); i++) {
                result[k++] = intermediate.get(i);
            }
        }
        return result;
    }

    int[] findDiagonalOrder_1(int[][] matrix) {
        //验空
        if (matrix == null || matrix.length == 0) {
            return new int[0];
        }
        int row = matrix.length;    //行数
        int col = matrix[0].length; //列数
        //返回的数组
        int[] resultArr = new int[row * col];
        resultArr[0] = matrix[0][0];
        boolean b = true;
        int getNext = 1;
        for (int i = 1; i < row * col; i++) {
            getNext = getNextNumber(getNext, row, col, b);
            if (getNext == row * col) {
                resultArr[i] = matrix[row - 1][col - 1];
                break;
            }
            if ((getNext < col && getNext % 2 == 0) ||
                    (getNext % col == 1 && (getNext / col) % 2 == 0) ||
                    (getNext % col == 0 && (getNext / col) % 2 == 0) ||
                    (getNext < row * col && getNext > row * col - col && getNext % 2 != 0)) {
                b = !b;
            }
            if (getNext % col == 0) {
                int x = col - 1;
                int y = getNext / col - 1;
                resultArr[i] = matrix[y][x];
            } else {
                int x = getNext % col - 1;
                int y = getNext / col;
                resultArr[i] = matrix[y][x];
            }
        }
        return resultArr;


    }

    /**
     * 获取下一个数字
     * @return
     */
    private int getNextNumber(int currNumber, int row, int col, boolean b) {
        //四个角的特殊数是
        //1                            col
        //(row-1)*col + 1              row*col
        if (b) {
            if (currNumber < col) {
                return ++currNumber;
            } else if (currNumber % col == 0) {
                return 2 * col;
            } else {
                return currNumber - col + 1;
            }
        } else {
            if (currNumber % col == 1 && currNumber != (row -1) * col + 1) {
                return currNumber + col;
            } else if ((row -1) * col + 1 <= currNumber && currNumber < row * col){
                return ++currNumber;
            } else {
                return currNumber + col - 1;
            }
        }
    }


}
