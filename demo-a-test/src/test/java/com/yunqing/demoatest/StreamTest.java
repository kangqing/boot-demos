package com.yunqing.demoatest;

import cn.hutool.core.util.ReUtil;
import cn.hutool.json.JSONUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author yx
 * @description
 * @date 2020/7/2 15:35
 */
@Slf4j
@SpringBootTest
public class StreamTest {

    private List<Student> list;
    private List<List<Student>> listFlat;

    @BeforeEach//注解在非静态方法上
    void init() {
        list = Arrays.asList(
                new Student(1, "盖伦1", 200, 66.0),
                new Student(2, "赵信", 21, 90.0),
                new Student(3, "乐芙兰1", 21, 90.0),
                new Student(4, "李青", 50, 100d),
                new Student(5, "泰达米尔", 600, 90d)
        );

        listFlat = Arrays.asList(
                Arrays.asList(
                        new Student(1, "盖伦1", 200, 66.0),
                        new Student(2, "赵信", 21, 90.0)
                ),
                Arrays.asList(
                        new Student(3, "乐芙兰1", 21, 90.0),
                        new Student(4, "李青", 50, 100d)
                ),
                Collections.singletonList(
                        new Student(5, "泰达米尔", 600, 90d)
                )
        );
    }

    /**
     * filter
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
     * map
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

    /**
     * flatMap
     * @DESC 将嵌套列表转换为普通列表，例如 List<List<Student>> 转化为 List<Student>
     * 1.将嵌套列表转化成普通列表，断言转化结果 = list
     * 2.转化后获取列表的 id 集合并打印
     * 3.转化后再提取集合的分数，求平均值，打印平均值
     */
    @Test
    void flatMapTest() {
        List<Student> collect = listFlat.stream().flatMap(Collection::stream)
                .peek(System.out::println)
                .collect(Collectors.toList());
        Assertions.assertEquals(collect, list);

        List<Integer> idList = listFlat.stream().flatMap(Collection::stream)
                .peek(System.out::println)
                .flatMapToInt(student -> IntStream.of(student.getId()))
                .peek(System.out::println)
                .boxed()
                .collect(Collectors.toList());
        Assertions.assertEquals(idList.size(), 5);

        listFlat.stream().flatMap(Collection::stream)
                .mapToDouble(Student::getScore)
                .average()
                .ifPresent(System.out::println);

    }

    /**
     * sorted
     * @DESC 排序
     * 1.先按照分数倒叙排列，如果分数相同按照年龄正序排列，如果年龄相同按照 id 正序排列，打印
     * 2.如果 reversed() 写在最后，则全部按照倒叙排列
     */
    @Test
    void sortedTest() {
        List<Student> collect = list.stream().sorted(Comparator.comparing(Student::getScore).reversed()
                .thenComparing(Student::getAge)
                .thenComparing(Student::getId))
                .peek(System.out::println)
                .collect(Collectors.toList());
        Assertions.assertEquals("李青", collect.get(0).getName());

        log.info("-------------------------------------------------------------");

        List<Student> collect2 = list.stream().sorted(Comparator.comparing(Student::getScore)
                .thenComparing(Student::getAge)
                .thenComparing(Student::getId).reversed())
                .peek(System.out::println)
                .collect(Collectors.toList());
        Assertions.assertEquals("李青", collect2.get(0).getName());
    }

    /**
     * match
     * @DESC 验证list中的每一项是否匹配我们的条件
     * allMatch 全都匹配
     * anyMatch 任意匹配
     * noneMatch 全不匹配
     *
     * 设置匹配条件为正则表达式 ^[1-9]\\d*$ 正整数
     * 1.验证list的id是否全都匹配条件
     * 2.临时改变list的第一个年龄为 -1 验证是否年龄任意一个匹配条件
     * 3.验证分数是否全不匹配条件
     */
    @Test
    void matchTest() {
        boolean b = list.stream().allMatch(item -> ReUtil.isMatch("^[1-9]\\d*$", item.getId().toString()));
        log.info("输出list的id是否全部匹配条件,结果：----------[{}]", b);

        list.get(0).setAge(-1);
        list.forEach(System.out::println);
        boolean b1 = list.stream().anyMatch(item -> ReUtil.isMatch("^[1-9]\\d*$", item.getAge().toString()));
        log.info("输出list的年龄是否任意匹配条件,结果：----------[{}]", b1);

        boolean b2 = list.stream().noneMatch(item -> ReUtil.isMatch("^[1-9]\\d*$", item.getScore().toString()));
        log.info("输出list的年龄是否都不匹配条件,结果：----------[{}]", b2);

    }

    /**
     * reduce
     * @DESC 合并流元素产生单个值
     * 1.计算list中所有学生年龄的总和，存在则打印
     * 2.计算list中所有学生年龄的总和，存在则打印
     */
    @Test
    void reduceTest() {
        list.stream().map(Student::getAge)
                .reduce(Integer::sum)
                .ifPresent(System.out::println);

        list.stream().map(Student::getScore)
                .reduce(Double::sum)
                .ifPresent(System.out::println);
    }

    /**
     * collector
     * 收集器，主要用于toList() / toSet() / toMap() / joining()连接字符串
     * 1.把list中的id作为key, name作为value转化成Map
     * 2.把学生名字用逗号拼接，并且第一个逗号用星号替换
     */
    @Test
    void collectorTest() {

        Map<Integer, String> map = list.stream().collect(Collectors.toMap(Student::getId, Student::getName));
        map.forEach((k, v) -> log.info("key = {} , value = {}", k, v));

        String s = list.stream().map(Student::getName)
                .collect(Collectors.joining(","))
                .replaceFirst(",", "*");
        log.info("输出逗号拼接的字符串-->{}", s);

    }

    /**
     * summarizingDouble
     * 1.计算集合中某个元素的 count / sum / avg / min / max
     * 2.第二种写法
     */
    @Test
    void summarizingDoubleTest() {
        DoubleSummaryStatistics collect = list.stream()
                .collect(Collectors.summarizingDouble(Student::getScore));
        System.out.println(collect);

        DoubleSummaryStatistics doubleSummaryStatistics = list.stream()
                .mapToDouble(Student::getScore)
                .summaryStatistics();
        System.out.println(doubleSummaryStatistics);
    }

    /**
     * partitioningBy
     * @DESC 用于分割列表
     * 把学生列表中年龄大于100的放进key为true的Map中
     * 小于等于100的放进key为false的Map中
     */
    @Test
    void partitioningByTest() {
        Map<Boolean, List<Student>> map = list.stream()
                .collect(Collectors.partitioningBy(e -> e.getAge() > 100));
        log.info("true列表 = {}", map.get(Boolean.TRUE));
        log.info("false列表 = {}", map.get(Boolean.FALSE));
    }

    /**
     * groupingBy
     * @DESC 分组
     * 1.根据成绩分组
     * 2.获取每个分数的人数
     * 3.根据成绩获取取得每个成绩的学生的总分
     */
    @Test
    void groupingByTest() {
        Map<Double, List<Student>> map1 = list.stream().collect(Collectors.groupingBy(Student::getScore));
        map1.forEach((k, v) -> System.out.println(k + "\t" + v));

        Map<Double, Long> map2 = list.stream().collect(Collectors.groupingBy(Student::getScore, Collectors.counting()));
        map2.forEach((k,v) -> System.out.println(k + "\t" + v));

        Map<Double, Double> map3 = list.stream()
                .collect(Collectors.groupingBy(Student::getScore, Collectors.summingDouble(Student::getScore)));
        map3.forEach((k, v) -> System.out.println(k + "\t" + v));
    }

    /**
     * parallel
     * @DESC 并发
     * 延迟一秒后，并发的打印出学生的名字
     */
    @Test
    void parallel() {
        list.stream().parallel().forEach(this::print);
    }

    private void print(Student student) {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("学生的名字是 ： {}", student.getName());
    }

    /**
     * @DESC 读写文件
     * 先把list中的数据写到student.txt中
     * 再读取出来
     */
    @Test
    void fileTest() throws IOException {
        PrintWriter printWriter = new PrintWriter(Files.newBufferedWriter(Paths.get("D://student.txt")));
        //list列表写出到文件student.txt中
        list.forEach(printWriter::println);
        printWriter.close();
        //读取文件
        List<String> collect = Files.lines(Paths.get("D://student.txt"))
                .peek(System.out::println)
                .collect(Collectors.toList());
        Assertions.assertEquals(5, collect.size());
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
