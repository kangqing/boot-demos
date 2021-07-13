package com.yunqing.demoatest;

import cn.hutool.core.collection.CollectionUtil;
import com.yunqing.demoatest.utils.CommonUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cglib.core.CollectionUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author kangqing
 * @since 2020/10/13 17:08
 */
@SpringBootTest
public class CommonUtilsTest {

    @Test
    void test() {
        List<String> dateArr = new ArrayList<>();
        String currentDate = CommonUtils.getCurrentDate();
        System.out.println(currentDate);
        String startDate = CommonUtils.getFutureDay(currentDate, "yyyy/MM/dd", -6);
        System.out.println(startDate);
        while(startDate.compareTo(currentDate) <= 0) {
            dateArr.add(startDate);
            startDate = CommonUtils.getFutureDay(startDate, "yyyy/MM/dd", 1);
        }
        
        dateArr.forEach(System.out::println);

        System.out.println("--------------------------");
        String futureMonth = CommonUtils.getFutureMonth("2020/01", "yyyy/MM", -1);
        System.out.println(futureMonth);
    }

    @Test
    void testList() throws IOException, ClassNotFoundException {
        List<Cat> list = new ArrayList<>();
        list.add(new Cat("1", 1));
        list.add(new Cat("2", 2));
        List<Cat> cats = new ArrayList<>();
        cats = deepCopy(list);
        list.get(0).setAge(100);
        cats.forEach(System.out::println);
        list.forEach(System.out::println);
    }


    public static <T> List<T> deepCopy(List<T> src) throws IOException, ClassNotFoundException {

        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(byteOut);
        out.writeObject(src);

        ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
        ObjectInputStream in = new ObjectInputStream(byteIn);
        @SuppressWarnings("unchecked")
        List<T> dest = (List<T>) in.readObject();
        return dest;
    }
}

@Data
@AllArgsConstructor
class Cat implements Serializable{
    private String name;
    private int age;
}
