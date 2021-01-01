package com.yunqing.demoleetcode.time.time202101;

/**
 * 1.1 2021元旦
 * @author kangqing
 * @since 2021/1/1 23:28
 */
public class Time1m1 {
    public static void main(String[] args) {
        final Solution1m1 s = new Solution1m1();
        System.out.println(s.canPlaceFlowers(new int[]{0, 1, 0}, 1));
        //[1,0,0,0,1,0,0]
        //2
    }
}

class Solution1m1 {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        // 每次跳两个格子
        for (int i = 0; i < flowerbed.length; i += 2) {
            // 如果当前为空地
            if (flowerbed[i] == 0) {
                // 如果是最后一格或者下一格为空
                if (i == flowerbed.length - 1 || flowerbed[i + 1] == 0) {
                    n--;
                } else {
                    i++;
                }
            }
        }
        return n <= 0;
    }
}
