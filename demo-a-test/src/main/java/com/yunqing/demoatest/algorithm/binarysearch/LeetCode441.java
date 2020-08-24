package com.yunqing.demoatest.algorithm.binarysearch;

/**
 * LeetCode441 排列硬币
 * @author yunqing
 * @since 2020/8/25 6:34
 */
public class LeetCode441 {
    public static void main(String[] args) {
        Solution441 solution441 = new Solution441();
        System.out.println(solution441.arrangeCoins(1804289383));
    }
}

class Solution441 {
    public int arrangeCoins(int n) {
        int low = 0;
        int high = n;
        while (low <= high) {
            long mid = (low + high) / 2;
            long count = getCount(mid);
            if (count > n) {
                high = (int) mid - 1;
            } else if (count < n) {
                low = (int) mid + 1;
            } else {
                return (int) mid;
            }
        }
        return high;
    }

    /**
     * 计算 mid 行需要的硬币数量
     * @param mid
     * @return
     */
    private long getCount(long mid) {
        return ((mid + 1) * mid) / 2;
    }
}
