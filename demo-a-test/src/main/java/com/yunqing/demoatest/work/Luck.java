package com.yunqing.demoatest.work;

import cn.hutool.core.date.DateUtil;

import java.util.*;
import java.util.stream.Collectors;


/**
 * @author kangqing
 * @since 2021/12/8 22:20
 */
public class Luck {
    public static void main(String[] args) {
        Luck luck = new Luck();
        int i = DateUtil.dayOfWeek(new Date());
        if (i == 1 || i == 3 || i == 5) {
            luck.ssq();
        } else if (i == 2 || i == 4 || i == 7) {
            luck.dlt();
        } else {
            System.out.println("休息");
        }

    }

    private void ssq() {
        Random random = new Random();
        Set<Integer> collect = new HashSet<>();
        while (collect.size() < 6) {
            int i = random.nextInt(33) + 1;
            collect.add(i);
        }
        collect = collect.stream().sorted().collect(Collectors.toCollection(LinkedHashSet::new));
        collect.add(random.nextInt(16) + 1);
        collect.forEach(System.out::println);
    }

    private void dlt() {
        Random random = new Random();
        Set<Integer> collect = new HashSet<>();
        while (collect.size() < 5) {
            int i = random.nextInt(35) + 1;
            collect.add(i);
        }
        collect = collect.stream().sorted().collect(Collectors.toCollection(LinkedHashSet::new));
        Set<Integer> lan = new HashSet<>();
        while (lan.size() < 2) {
            int i = random.nextInt(12) + 1;
            lan.add(i);
        }
        lan = lan.stream().sorted().collect(Collectors.toCollection(LinkedHashSet::new));
        collect.addAll(lan);
        collect.forEach(System.out::println);
    }
}
