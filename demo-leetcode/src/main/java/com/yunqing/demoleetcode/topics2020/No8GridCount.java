package com.yunqing.demoleetcode.topics2020;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 岛屿数量：广度优先搜索
 * @author kangqing
 * @since 2021/2/1 21:38
 */
public class No8GridCount {
    public static void main(String[] args) {
        //char[][] arr = new char[][]{{'1','1','0','0','0'},{'1','1','0','0','0'},{'0','0','1','0','0'},{'0','0','0','1','1'}};
        char[][] bArr = new char[][]{{'1','1','0','0','0'},{'1','1','0','0','0'},{'0','0','1','0','0'},{'0','0','0','1','1'}};

        SolutionNo8 solutionNo8 = new SolutionNo8();
        System.out.println(solutionNo8.numIslands(bArr));
    }
}

class SolutionNo8 {
    public int numIslands(char[][] grid) {
        int count = 0;
        // 长
        int row = grid.length;
        // 宽
        int col = grid[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == '1') {
                    // 如果当前是土地则进行广度优先搜索
                    bfs(grid, i, j, row, col);
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * 广度优先搜索
     * @param grid 二维数组
     * @param i 横向索引
     * @param j 纵向索引
     * @param row 长
     * @param col 宽
     */
    private void bfs(char[][] grid, int i, int j, int row, int col) {
        // 定义一个队列
        Deque<int[]> deque = new ArrayDeque<>();
        deque.offer(new int[]{i, j});
        while (!deque.isEmpty()) {
            int[] poll = deque.poll();
            i = poll[0];
            j = poll[1];
            if (i >= 0 && i < row && j >= 0 && j < col && grid[i][j] == '1') {
                grid[i][j] = 'A';
                deque.offer(new int[]{i, j + 1});
                deque.offer(new int[]{i, j - 1});
                deque.offer(new int[]{i + 1, j});
                deque.offer(new int[]{i - 1, j});
            }
        }
    }
}
