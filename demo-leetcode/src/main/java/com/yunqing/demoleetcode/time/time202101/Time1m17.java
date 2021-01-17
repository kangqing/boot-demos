package com.yunqing.demoleetcode.time.time202101;


/**
 * @author kangqing
 * @since 2021/1/17 20:08
 */
public class Time1m17 {
    public static void main(String[] args) {
        //[[1,-8],[2,-3],[1,2]]
        int [][] arr = {{1,-8}, {2,-3}, {1,2}};
        Solution1m17 solution1m17 = new Solution1m17();
        System.out.println(solution1m17.checkStraightLine(arr));
    }
}

class Solution1m17 {
    public boolean checkStraightLine(int[][] coordinates) {
        int deltaX = coordinates[0][0], deltaY = coordinates[0][1];
        int n = coordinates.length;
        for (int i = 0; i < n; i++) {
            coordinates[i][0] -= deltaX;
            coordinates[i][1] -= deltaY;
        }
        int A = coordinates[1][1], B = -coordinates[1][0];
        for (int i = 2; i < n; i++) {
            int x = coordinates[i][0], y = coordinates[i][1];
            if (A * x + B * y != 0) {
                return false;
            }
        }
        return true;
    }
}