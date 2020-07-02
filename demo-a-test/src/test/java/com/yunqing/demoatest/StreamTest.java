package com.yunqing.demoatest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yx
 * @description
 * @date 2020/7/2 15:35
 */
@Slf4j
@SpringBootTest
public class StreamTest {

    private List<Student> list;

    @BeforeEach//注解在非静态方法上
    void init() {
        list = Arrays.asList(
                new Student(1, "盖伦1", 20, 66.0),
                new Student(2, "赵信", 21, 80.5),
                new Student(3, "乐芙兰1", 202, 90.0),
                new Student(4, "李青", 50, 100d),
                new Student(5, "泰达米尔", 600, 90d)
        );
    }

    /**
     * Filter
     * @DESC 过滤出list中学生名字中包括 "1" 的学生集合，
     *       并使用peek()利用其返回Stream<Student>直接以流的方式实现打印过滤出的学生
     *
     *       peek() 和 ForEach() 的区别：
     *       前者返回Stream<T> 可在流的基础上继续流操作
     *       后者返回void,想要继续流操作需要进行二次流处理
     */
    @Test
    void filterTest() {
        final List<Student> collect = list.stream().filter(item -> item.getName().contains("1"))
                .peek(System.out::println)
                .collect(Collectors.toList());
        Assertions.assertEquals(2, collect.size());

        /**
         * 获取过滤的第一条数据，如果包含则打印
         */
        list.stream().filter(item -> item.getName().contains("1"))
                .findFirst()
                .ifPresent(System.out::println);
    }

    /**
     * Map
     * @DESC 可以将List里面的对象转化成新的对象
     * 1.将学生集合里不小于90分的作为精英学生过滤出来,并打印
     * 2.获取所有学生的 name 集合,并打印
     */
    @Test
    void mapTest() {
        final List<EliteStudent> collect = list.stream().filter(item -> item.getScore() >= 90)
                .map(student -> {
                    EliteStudent eliteStudent = new EliteStudent();
                    BeanUtils.copyProperties(student, eliteStudent);
                    return eliteStudent;
                }).peek(System.out::println)
                .collect(Collectors.toList());
        Assertions.assertEquals(3, collect.size());

        final List<String> nameList = list.stream().map(Student::getName)
                .peek(System.out::println)
                .collect(Collectors.toList());
        Assertions.assertEquals(5, nameList.size());

    }


}


@Data
@AllArgsConstructor
class Student {
    private Integer id;
    private String name;
    private Integer age;
    private Double score;
}

@Data
class EliteStudent {
    private String name;
    private Double score;
}
