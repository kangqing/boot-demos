package com.yunqing.demoatest.algorithm.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * LeetCode1337 方阵中战斗力最弱的 K 行
 * @author yunqing
 * @since 2020/8/25 19:53
 */
public class LeetCode1337 {
    public static void main(String[] args) {

    }
}

class Solution1337 {
    public int[] kWeakestRows(int[][] mat, int k) {
        int row = mat.length;
        int col = mat[0].length;
        int[] jun = new int[row];
        List<Integer> res = new ArrayList<>();
        int i = 0;
        for (int[] arr : mat) {
            int count = Arrays.stream(arr).filter(x -> x == 1).sum();
            jun[i ++] = count;
        }
        int[] jun_copy = jun.clone();
        Arrays.sort(jun_copy);
        for (int x : jun_copy) {
            if (res.size() == k) {
                break;
            }
            int index = getIndex(jun, x);
            res.add(index);
            jun[index] = -1;
        }
        Integer[] integers = res.toArray(new Integer[0]);
        return Arrays.stream(integers).mapToInt(Integer::valueOf).toArray();
    }

    private int getIndex(int[] arr, int x) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == x) {
                return i;
            }
        }
        return -1;
    }
}
