package com.yunqing.demohutool;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author kangqing
 * @description
 * @date 2020/5/21 9:24
 */
@SpringBootTest
public class OtherTest {

    @Test
    void finalTest() {
        final List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.forEach(System.out::println);

        List<String> collect = list.stream().filter(e -> e.equals("1")).collect(Collectors.toList());
        collect.forEach(System.out::println);
    }
}
