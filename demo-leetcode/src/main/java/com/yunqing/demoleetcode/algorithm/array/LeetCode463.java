package com.yunqing.demoleetcode.algorithm.array;

/**
 * LeetCode463 岛屿的周长
 * @author yx
 * @since 2020/10/30 17:20
 */
public class LeetCode463 {
    public static void main(String[] args) {

    }
}

class Solution463 {
    public int islandPerimeter(int[][] grid) {
        int row = grid.length;//行
        int col = grid[0].length;//列
        int res = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 0) continue;
                if (grid[i][j] == 1) {
                    //判断周围水域数量
                    res += get0Count(grid, i, j, row, col);
                }
            }
        }
        return res;

    }

    /**
     * 计算周围水域的数量，就是此块陆地需要计算的周长
     * @param grid
     * @param i
     * @param j
     * @param row
     * @param col
     * @return
     */
    private int get0Count(int[][] grid, int i, int j, int row, int col) {
        int res = 0;
        if (i - 1 < 0) res++;
        else {
            if (grid[i - 1][j] == 0) res++;
        }
        if (i + 1 == row) res++;
        else {
            if (grid[i + 1][j] == 0) res++;
        }
        if (j - 1 < 0) res++;
        else {
            if (grid[i][j - 1] == 0) res++;
        }
        if (j + 1 == col) res++;
        else {
            if (grid[i][j + 1] == 0) res++;
        }
        return res;
    }
}
