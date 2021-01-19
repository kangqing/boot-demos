package com.yunqing.demoleetcode.algorithm.string;

/**
 * LeetCode925 长按键入
 * @author kangqing
 * @since 2020/10/21 14:06
 */
public class LeetCode925 {
    public static void main(String[] args) {
        Solution925 solution925 = new Solution925();
        boolean longPressedName = solution925.isLongPressedName("pyplrz", "ppyypllr");
        System.out.println(longPressedName);
    }
}

class Solution925 {
    public boolean isLongPressedName(String name, String typed) {
        int i = 0; int j = 0;
        while (j < typed.length()) {
            if (i < name.length() && typed.charAt(j) == name.charAt(i)) {
                i++;
                j++;
            } else if (j > 0 && typed.charAt(j) == typed.charAt(j - 1)) {
                j++;
            } else {
                return false;
            }
        }
        return i == name.length();
    }
}
