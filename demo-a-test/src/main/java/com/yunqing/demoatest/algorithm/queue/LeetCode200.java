package com.yunqing.demoatest.algorithm.queue;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Description LeetCode 200 岛屿数量
 * @Author yunqing
 * @Data 2020/7/13 23:29
 */
public class LeetCode200 {

    public static void main(String[] args) {
        char[][] arr = new char[][]{{'1','1','0','0','0'},{'1','1','0','0','0'},{'0','0','1','0','0'},{'0','0','0','1','1'}};
        char[][] bArr = new char[][]{{'1','1','0','0','0'},{'1','1','0','0','0'},{'0','0','1','0','0'},{'0','0','0','1','1'}};
        DFS d = new DFS();
        System.out.println(d.numIslands(arr));

        BFS b = new BFS();
        System.out.println(b.numIslands(bArr));
    }
}

/**
 * 广度优先搜索
 * 队列，当队列不是空，不断以此点为中心进行广度优先搜索，并加入队列
 */
class BFS {
    public int numIslands(char[][] grid) {
        int count = 0;//岛屿数量
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] == '1') {
                    breadFirstSearch(grid, i, j);
                    count++;
                }
            }
        }

        return count;
    }
    /**
     * 广度优先搜索
     */
    private void breadFirstSearch(char[][] grid, int i, int j) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {i, j});
        while(!queue.isEmpty()) {
            //如果队列不为空，出队列，并置为0
            int[] arr = queue.remove();//出队列返回出去的元素
            i = arr[0];
            j = arr[1];
            if(0 <= i && i < grid.length && 0 <= j && j < grid[i].length && grid[i][j] == '1') {
                grid[i][j] = '0';//出队列的值置为0
                //进行广度优先搜索，上下左右的无论陆地还是水都加入队列,进行while循环
                queue.offer(new int[] {i, j + 1});
                queue.offer(new int[] {i, j - 1});
                queue.offer(new int[] {i + 1, j});
                queue.offer(new int[] {i - 1, j});
            }
        }

    }
}

/**
 * 深度优先搜索，递归
 */
class DFS {
    public int numIslands(char[][] grid) {

        int count = 0;//岛屿数量
        for(int i = 0; i < grid.length; ++i) {
            for(int j = 0; j < grid[0].length; ++j) {
                if(grid[i][j] == '1') {
                    ++count;
                    dfsMethod(grid, i , j);
                }
            }
        }

        return count;
    }

    /**
     * 递归进行深度优先搜索
     */
    private void dfsMethod(char[][] grid, int i, int j) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] == '0') {
            return;
        }
        grid[i][j] = '0';
        dfsMethod(grid, i, j + 1);
        dfsMethod(grid, i, j - 1);
        dfsMethod(grid, i + 1, j);
        dfsMethod(grid, i - 1, j);
    }

}
