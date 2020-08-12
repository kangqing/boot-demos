package com.yunqing.demoatest.readjdk.java.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author yunqing
 * @since 2020/8/12 21:14
 */
public class ArrayListTest {
    public static void main(String[] args) {
        testForeachAddOrRemove();
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
