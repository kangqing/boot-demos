package com.yunqing.demoleetcode.time;

import java.math.BigInteger;
import java.util.Arrays;

/**
 * 二维数组，二进制转10进制
 * @author yx
 * @since 2020/12/7 15:12
 */
public class Time12m7 {
    public static void main(String[] args) {
        Solution12m7 s = new Solution12m7();
        System.out.println(s.matrixScore(new int[][]{{0,0,1,1}, {1,0,1,0}, {1,1,0,0} }));
    }
}

class Solution12m7 {
    public int matrixScore(int[][] A) {
        int res = 0;
        if (A.length == 0) {
            return 0;
        }
        // 行、列
        int row = A.length;
        int col = A[0].length;
        // 如果要十进制最大则，第一行全部翻转为1
        for (int i = 0; i < row; i++) {
            if (A[i][0] == 0) {
                for (int j = 0; j < col; j++) {
                    if (A[i][j] == 0) {
                        A[i][j] = 1;
                    } else {
                        A[i][j] = 0;
                    }
                }
            }
        }
        System.out.println(Arrays.deepToString(A));
        // 之后遍历每一列，列中 0 多则翻转
        for (int i = 1; i < col; i++) {
            int zero = 0;
            int one = 0;
            for (int j = 0; j < row; j++) {
                if (A[j][i] == 0) zero++;
                if (A[j][i] == 1) one++;
            }
            if (zero > one) {
                for (int j = 0; j < row; j++) {
                    if (A[j][i] == 0) A[j][i] = 1;
                    else A[j][i] = 0;
                }
            }
        }
        System.out.println(Arrays.deepToString(A));
        for (int[] ints : A) {
            StringBuilder sb = new StringBuilder();
            for (int anInt : ints) {
                sb.append(anInt);
            }
            System.out.println(sb);
            // 二进制转10进制，太大的数用BigInteger
            // long lon = Long.parseLong(sb.toString(), 2);
            // 如果需要转换的数太大，可以使用BigInteger从任意进制转成任意进制
            String str = new BigInteger(sb.toString(), 2).toString(10);
            int lon = Integer.parseInt(str);
            res += lon;
        }
        return res;
    }
}