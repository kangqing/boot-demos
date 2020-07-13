package com.yunqing.demoatest.algorithm.queue;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Description LeetCode 200 岛屿数量
 * @Author yunqing
 * @Data 2020/7/13 23:29
 */
public class DaoyuTest {
    public int numIslands(char[][] grid) {
        int count = 0;//岛屿数量
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[i].length; j++) {
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
