package com.yunqing.demoatest.readjdk.java.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author kangqing
 * @since 2021/4/26 10:26
 */
public class ListRemoveTest {
    public static void main(String[] args) {
        List<Cat> list = new ArrayList<>(List.of(new Cat("1", 11), new Cat("2", 22)));
        Iterator<Cat> iterator = list.iterator();
        while (iterator.hasNext()) {
            Cat next = iterator.next();
            if (next.getAge() == 11) {
                iterator.remove();
            }else {
                next.setName("666");
                next.setAge(6);
            }
        }
        System.out.println(list);
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class Cat {
    private String name;
    private int age;
}
