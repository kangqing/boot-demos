package com.yunqing.demoleetcode.time;

import java.util.HashSet;
import java.util.Set;

/**
 * 12.13 HashSet
 * @author yunqing
 * @since 2020/12/13 10:24
 */
public class Time12m13 {
    public static void main(String[] args) {

    }
}

class Solution12m13 {
    public boolean containsDuplicate(int[] nums) {
        if(nums == null) return false;
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        return !(set.size() == nums.length);
    }
}
