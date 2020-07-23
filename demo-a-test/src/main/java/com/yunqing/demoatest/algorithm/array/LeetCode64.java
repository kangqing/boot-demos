package com.yunqing.demoatest.algorithm.array;

/**
 * @Description LeetCode64 最小路径和
 * @Author yunqing
 * @Data 2020/7/24 6:07
 */
public class LeetCode64 {
    public static void main(String[] args) {
        //[[1,3,1],[1,5,1],[4,2,1]]
        int[][] arr = new int[][]{{0, 1}, {1, 0}};
        Solution64 s = new Solution64();
        System.out.println(s.minPathSum(arr));
    }
}

/**
 * 解决方案思路：
 * 因为只能向下或者向右移动，所以考虑，第一行只能是其左边的数字移动而来，所以第一行每个数变成当前数 + 前一个数
 * 第一列只能是上面数字来，所以第一列每个数变成当前数 + 上一个数
 * 剩下的数只有可能从上或者左面来，所以每一个数变成 Math.min(上面数，左面数) + 当前数
 */
class Solution64 {
    public int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;
        int arr[][] = new int[m][n];
        arr[0][0] = grid[0][0];
        for (int i = 1; i < m; i++) {
            arr[i][0] = arr[i - 1][0] + grid[i][0];
        }
        for (int j = 1; j < n; j++) {
            arr[0][j] = arr[0][j - 1] + grid[0][j];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                arr[i][j] = Math.min(arr[i - 1][j], arr[i][j - 1]) + grid[i][j];
            }
        }
        return arr[m - 1][n - 1];

    }
}
