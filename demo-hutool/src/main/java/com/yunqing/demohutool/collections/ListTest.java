package com.yunqing.demohutool.collections;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.ListUtil;
import com.google.common.collect.Lists;
import java.util.List;

/**
 * @author kangqing
 * @since 2023/8/22 16:06
 */
public class ListTest {
    public static void main(String[] args) {

        List<String> guava = Lists.newArrayList("aa", "bb", "cc");
        List<String> hutool = CollUtil.newArrayList("aa", "bb", "cc");

        /**
         * 反转集合
         */
        final List<String> reverse = Lists.reverse(guava);
        System.out.println("----------列表反转guava----------");
        reverse.forEach(System.out::println);
        CollUtil.reverse(hutool);
        System.out.println("----------列表反转hutool----------");
        hutool.forEach(System.out::println);

        /**
         * 分解字符串成字符列表
         */
        List<Character> chars2 = Lists.charactersOf("guava");
        System.out.println("guava分解字符串-----字符列表");
        chars2.forEach(System.out::println);

        /**
         * 分组
         */
        List<String> names = Lists.newArrayList("John","Jane","Adam","Tom","Viki","Tyler");
        // guava
        List<List<String>> result0 = Lists.partition(names, 2);
        // hutool
        List<List<String>> result1 = ListUtil.partition(names, 2);
        System.out.println("------------列表分组guava-----------");
        result0.forEach(System.out::println);
        System.out.println("------------列表分组hutool-----------");
        result1.forEach(System.out::println);


    }

}
