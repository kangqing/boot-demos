package com.yunqing.demoatest.hutools;

import cn.hutool.core.collection.ListUtil;
import com.yunqing.demoatest.controller.Cat;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author kangqing
 * @since 2023/10/26 06:43
 */
@Slf4j
public class ListUtilTest {
    public static void main(String[] args) {
        List<Integer> integers = new ArrayList<>();
        integers.add(1);
        integers.add(2);
        integers.add(1);
        integers.add(2);
        integers.add(1);
        integers.add(2);
        final List<List<Integer>> split = ListUtil.split(integers, 4);
        split.forEach(System.out::println);

        log();
    }

     public static void log() {
        List<Cat> list = new ArrayList<>();
        list.add(new Cat(1, "AAA"));
        list.add(new Cat(2, "BBB"));
        for (int i = 0; i < list.size(); i++) {
            log.info("ggggg + {}, {}", i, list.get(i));
        }

         System.out.println(list);

        log.info("猫id是： {}", list.stream().map(Cat::getId).map(String::valueOf).collect(Collectors.joining(",")));
     }
}
