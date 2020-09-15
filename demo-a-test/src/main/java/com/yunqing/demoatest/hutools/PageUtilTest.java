package com.yunqing.demoatest.hutools;

import cn.hutool.core.lang.Console;
import cn.hutool.core.util.PageUtil;

import java.util.Arrays;

/**
 * @author yx
 * @since 2020/9/15 16:03
 */
public class PageUtilTest {
    public static void main(String[] args) {
        /**
         * 第二页，每页10条
         * pageUtil默认0是第一页
         */
        int[] ints = PageUtil.transToStartEnd(2 - 1, 10);
        System.out.println(Arrays.toString(ints));

        //根据总数计算出总页数
        int i = PageUtil.totalPage(98, 10);
        Console.log("总页数 = {}", i);

        //分页彩虹算法,当前页、总页数、每屏展示的页数
        int[] rainbow = PageUtil.rainbow(7, 10, 5);
        Console.log("当前展示的页码 = {}", Arrays.toString(rainbow));
    }
}
