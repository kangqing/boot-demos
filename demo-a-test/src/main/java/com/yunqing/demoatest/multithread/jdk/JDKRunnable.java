package com.yunqing.demoatest.multithread.jdk;


import cn.hutool.core.lang.Console;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author kangqing
 * @since 2021/2/2 15:18
 */
public class JDKRunnable {
    public static void main(String[] args) {
        // 线程 1
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("JDK8之前的写法");
            }
        };
        // 线程 2
        Runnable r2 = () -> System.out.println("JDK8之后的写法");

        new Thread(r1).start();
        new Thread(r2).start();

        //method();
        stream();
    }

    /**
     * 实现 treeSet 的比较器排序
     */
    private void comparator() {
        // TreeSet 是一个有序的集合，它的作用是提供有序的Set集合。这是使用比较器排序。
        TreeSet<String> treeSet = new TreeSet<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length() - o2.length();
            }
        });
        // Lambda表达式写法
        TreeSet<String> treeSet1 = new TreeSet<>((m, n) -> m.length() - n.length());
        // 方法引用写法
        TreeSet<String> treeSet2 = new TreeSet<>(Comparator.comparingInt(String::length));
    }

    private static void method() {
        /**
         * 对象引用::实例方法名
         *
         * System.out就是一个PrintStream类型的对象引用，而println则是一个实例方法名，需要注意的是没有括号的哟。
         * 其中Consumer是Java内置函数式接口，下面的例子用到的都是Java内置函数式接口。Consumer中的唯一抽象方法accept方法参数列表
         * 与println方法的参数列表相同，都是接收一个String类型参数。
         */
        Consumer<String> consumer = System.out::println;
        consumer.accept("方法引用 1 之对象引用::实例方法名");

        /**
         * 类名::静态方法名
         *
         * Math 是一个类而 abs 为该类的静态方法。Function中的唯一抽象方法 apply 方法参数列表与 abs 方法的参数列表相同，
         * 都是接收一个 Integer 类型参数。
         */
        Function<Integer, Integer> f = Math::abs;
        final Integer apply = f.apply(-3);
        System.out.println(apply);

        /**
         * 类名::实例方法名
         *
         * String是一个类而equals为该类的定义的实例方法。BiPredicate中的唯一抽象方法test方法参数列表与equals方法的参数列表相同，
         * 都是接收两个String类型参数。
         */
        BiPredicate<String, String> n = String::equals;
        final boolean test = n.test("aaa", "bbb");
        System.out.println(test);

        /**
         * 引用构造器
         *
         * Function接口的apply方法接收一个参数，并且有返回值。在这里接收的参数是Integer类型，
         * 与StringBuffer类的一个构造方法StringBuffer(int capacity)对应，而返回值就是StringBuffer类型。
         * 上面这段代码的功能就是创建一个Function实例，并把它apply方法实现为创建一个指定初始大小的StringBuffer对象。
         */
        Function<Integer, StringBuffer> is = StringBuffer::new;
        final StringBuffer sb = is.apply(10);
        System.out.println(sb.capacity());

        /**
         * 引用数组
         *
         * 引用数组和引用构造器很像，格式为 类型[]::new，其中类型可以为基本类型也可以是类。如
         */
        Function<Integer, int[]> fii = int[]::new;
        final int[] apply1 = fii.apply(20);
        System.out.println(apply1.length);

        Function<Integer, Double[]> fid = Double[]::new;
        final int[] apply2 = fii.apply(30);
        System.out.println(apply2.length);

    }

    /**
     * lambda表达式方法体使用局部变量必须是final的
     */
    public void finalLambda() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        int[] arr = new int[2];
        AtomicInteger index = new AtomicInteger(0);
        list.forEach(e -> {
            arr[index.getAndIncrement()] = e;
        });
        System.out.println(Arrays.toString(arr));
    }


    private static void stream() {
        int[] arr = {4, 1, 2, 5, 0, 8, 6, 5};
        // 获取最大值
        final int max = Arrays.stream(arr).max().getAsInt();
        System.out.println(max);
        // 数组中大于3的元素的数量
        final long count = Arrays.stream(arr).filter(e -> e > 3).count();
        System.out.println(count);

        List<Student> list = Arrays.asList(
                new Student(1, "花木兰", 25, 66.0),
                new Student(2, "李白", 21, 90.0),
                new Student(3, "诸葛亮", 21, 80.0),
                new Student(4, "公孙离", 18, 100d),
                new Student(5, "不知火舞", 21, 90d),
                new Student(5, "不知火舞", 21, 90d)
        );

        list.stream().filter(e -> e.getScore() >= 90)
                .findFirst()
                .ifPresent(System.out::println);
        Console.log("-------------------------");

        list.stream().skip(1).limit(2).forEach(System.out::println);
        Console.log("-------------------------");

        list.stream().skip(3).distinct().forEach(System.out::println);
        Console.log("-------------------------");

        list.stream().mapToInt(Student::getAge).min().ifPresent(System.out::println);
        Console.log("-------------------------");

        final Set<String> collect = list.stream().map(Student::getName).collect(Collectors.toSet());
        System.out.println(collect);
        Console.log("-------------------------");

        final List<Student> collect1 = list.stream().sorted(Comparator.comparingInt(Student::getAge))
                .collect(Collectors.toList());
        System.out.println(collect1);
        Console.log("-------------------------");


    }
}

/**
 * 定义学生类
 */
@Data
@AllArgsConstructor
class Student {
    private Integer id;
    private String name;
    private Integer age;
    private Double score;
}

