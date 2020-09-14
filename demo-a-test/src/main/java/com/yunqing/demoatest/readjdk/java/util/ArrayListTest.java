package com.yunqing.demoatest.readjdk.java.util;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.*;

/**
 * @author yunqing
 * @since 2020/8/12 21:14
 */
@SuppressWarnings("unchecked")
public class ArrayListTest {
    public static void main(String[] args) {
        testForeachAddOrRemove();
        // 声明一个ArrayList,默认初始容量10，在添加第一个元素的时候变化
        ArrayList<Integer> list = new ArrayList<>();
        System.out.println("---------------ArrayList(Collection<? extends E> c)------------构造函数");
        Deque<Integer> deque = new ArrayDeque<>();
        deque.push(1);
        //因为ArrayDeque实现了Collection接口，所以可以调用构造函数把Deque中的元素转化成ArrayList
        System.out.println(new ArrayList<>(deque));

        //trimToSize : 将该 ArrayList 实例的容量调整为列表的当前大小。应用程序可以使用此操作来最大程度地减少 ArrayList 实例的存储
        list.add(1);
        list.trimToSize();
        //元素数量，是否为空,是否包含某个元素, （第一个指定元素的索引，最后一个指定元素的索引（没有返回-1））
        int size = list.size();
        boolean empty = list.isEmpty();
        boolean contains = list.contains(1);
        list.indexOf(1);
        list.lastIndexOf(1);
        //浅复制,只复制引用类型的内存地址，所以更改复制前的，复制后的也会改变
        ArrayList<Person> people = new ArrayList<>();
        people.add(new Person("小米", 11));
        ArrayList<Person> clone = (ArrayList<Person>) people.clone();
        people.get(0).setAge(12);
        System.out.println(clone.get(0).toString());
        //返回一个数组，以正确的顺序包含列表中的所有元素
        Object[] resArr = list.toArray();
        System.out.println(Arrays.toString(resArr));
        //get set remove add 就不说了
        //clear() 清空列表
        people.clear();
        //追加集合
        list.addAll(deque);
        //在索引后追加
        list.addAll(1, deque);
        System.out.println(list);
        //删除指定集合中的所有元素
        list.add(2);
        list.removeAll(deque);
        System.out.println(list);
        //删除所有不包含在指定集合中的元素
        list.addAll(deque);
        list.retainAll(deque);
        System.out.println(list);



    }

    /**
     * 验证不能在增强for循环中执行add()和remove()
     */
    private static void testForeachAddOrRemove() {
        var list = new ArrayList<String>();
        list.add("1");
        list.add("2");
        /*for (var s : list) {
            if ("2".equals(s)) {
                list.remove(s);
            }
        }*/
        /*var iterator = list.iterator();
        while (iterator.hasNext()) {
            var str = iterator.next();
            if ("2".equals(str)) {
                iterator.remove();
            }
        }*/

        list.removeIf("2"::equals);
        list.forEach(System.out::println);
    }
}

@Data
@AllArgsConstructor
class Person {
    private String name;
    private int age;
}
