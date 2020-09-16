package com.yunqing.demoatest.readjdk.java.util;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Spliterator;
import java.util.stream.Collectors;

/**
 * @author yx
 * @since 2020/9/15 17:28
 */
public class ArraysTest {
    public static void main(String[] args) {
        Integer[] arr = new Integer[]{3, 1, 3, 2, 4, 6, 5};
        System.out.println(Arrays.toString(arr));
        //针对基本数据类型   指定范围排序数组
        Arrays.sort(arr, 2, 5);
        System.out.println(Arrays.toString(arr));
        //针对基本数据类型   排序数组
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));

        //并行排序，区别于sort()的地方是，数据量大的时候此性能更好
        Arrays.parallelSort(arr);
        Arrays.parallelSort(arr, 2, 5);

        //根据比较器的顺序进行排序，必须是引用类型
        Arrays.sort(arr, (m, n) -> n - m);
        System.out.println("逆序" + Arrays.toString(arr));
        Arrays.sort(arr, Comparator.comparingInt(Integer::intValue).reversed());
        System.out.println("还是逆序" + Arrays.toString(arr));
        Arrays.sort(arr, 2, 7, Comparator.comparing(m -> m));
        System.out.println("索引2-7位正序" + Arrays.toString(arr));

        //并行的累加每个元素，(1,2,3,4) -> (1,3,6,10)
        Arrays.parallelPrefix(arr, Integer::sum);
        System.out.println(Arrays.toString(arr));

        Arrays.parallelPrefix(arr, 2, 7, Integer::sum);
        System.out.println("从索引2-7进行累加" + Arrays.toString(arr));

        //使用二进制查找找到指定值，**********需要先对数组进行排序**********
        int i = Arrays.binarySearch(arr, 11);
        System.out.println("11的索引位置是" + i);
        //如果有相同的元素,找到第一个
        Integer[] array = new Integer[]{11, 11, 11, 14, 14,  15, 18};
        i = Arrays.binarySearch(array, 0, 7, 14);
        System.out.println("14在索引的位置是" + i);
        //如果没有找到关键字，返回值为负的插入点值，所谓插入点值就是第一个比关键字大的元素在数组中的位置索引，而且这个位置索引从1开始
        i = Arrays.binarySearch(array, 13);
        System.out.println("未找到13则返回负数插入点值，插入点值从1开始的负数" + i);

        //如果两个数组的相同位置的元素全相等，则true,两个都为null也是true
        System.out.println(Arrays.equals(arr, array));
        System.out.println(Arrays.equals(new int[3], new int[3]));

        //用制定值填充数组的每一个元素
        Arrays.fill(arr, 1);
        System.out.println(Arrays.toString(arr));
        //填充指定索引位置
        Arrays.fill(arr, 2, 7, 10);
        System.out.println(Arrays.toString(arr));

        //复制源数组到目标长度，截断或补null,可自定义返回数组类型
        Integer[] integers = Arrays.copyOf(array, 10);
        System.out.println(Arrays.toString(integers));
        //截断
        integers = Arrays.copyOf(array, 3);
        System.out.println(Arrays.toString(integers));
        //复制指定索引之间的数组
        integers = Arrays.copyOfRange(array, 2, 7);
        System.out.println(Arrays.toString(integers));

        //转化为列表
        List<Integer> list = Arrays.asList(array);
        System.out.println("输出转化为list = " + list);
        //输出多维数组
        int[][] erArr = new int[][]{{1, 2 ,3}, {4, 5, 6}};
        System.out.println(Arrays.deepToString(erArr));

        //使用指定计算每个元素
        Arrays.setAll(array, e -> array[e] * array[e]);
        System.out.println(Arrays.toString(array));

        //并行的计算每个元素
        Arrays.parallelSetAll(array, e -> Double.valueOf(Math.sqrt(array[e])).intValue());
        System.out.println(Arrays.toString(array));

        //并行遍历迭代器，forEachRemaining操作每一个元素
        Spliterator<Integer> spliterator = Arrays.spliterator(array);
        spliterator.forEachRemaining(System.out::println);

        //流操作
        List<Integer> collect = Arrays.stream(array).filter(e -> e > 13).collect(Collectors.toList());
        System.out.println(collect);


    }
}
