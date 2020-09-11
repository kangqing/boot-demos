package com.yunqing.demoleetcode.algorithm.binarysearch;

import java.util.Random;

/**
 * LeetCode278 第一个错误的版本
 * @author yunqing
 * @since 2020/8/15 9:41
 */
public class LeetCode278 {
    public static void main(String[] args) {
        Solution278 solution278 = new Solution278();
        System.out.println("求解第一个错误的版本是 = " + solution278.firstBadVersion(1000));
    }
}

class Solution278 extends VersionControl {
    public int firstBadVersion(int n) {
        var left = 1;
        var right = n;
        while (left < right) {
            var mid = left + (right - left) / 2;
            var b = isBadVersion(mid);
            //false当前版本没错
            if (!b) left = mid + 1;
            //当前版本有错，保证mid右边不可能为第一个错误版本
            else right = mid;
        }
        return left;
    }
}

class VersionControl implements VC{
    //1 - 1000
    final static int x = new Random().nextInt(1000) + 1;
    static {
        System.out.println("第一个错误的版本是 = " + x);
    }
    @Override
    public boolean isBadVersion(int version) {
        return version >= x;
    }
}

interface VC {
    boolean isBadVersion(int version);
}