package com.yunqing.demojsonresult;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

@SpringBootTest
@Slf4j
class DemoJsonResultApplicationTests {

    @Test
    public void helloWorld() {
        final int[] arrs = {-229985452, -147909649};
        for (final int arr : arrs) {
            final Random random = new Random(arr);
            while (true) {
                final int n = random.nextInt(27);
                if (n == 0) break;
                System.out.print((char) ('`' + n));
            }
            System.out.print(" ");
        }
    }

    @Test
    void contextLoads() {
        List<String> arrayList = new ArrayList<>();
        log.info("数组的默认容量是{}", getArrayListCapacity((ArrayList<?>) arrayList));
        arrayList.add("1");
        log.info("添加一个元素后数组的默认容量是{}", getArrayListCapacity((ArrayList<?>) arrayList));
        for (int i = 0; i < 10; i++) {
            arrayList.add("元素" + i);
        }
        log.info("添加一个元素后数组的默认容量是{}", getArrayListCapacity((ArrayList<?>) arrayList));

    }

    /**
     * 反射获取数组的容量
     * @param arrayList
     * @return
     */
    public static int getArrayListCapacity(ArrayList<?> arrayList) {
        Class<ArrayList> arrayListClass = ArrayList.class;
        try {
            Field field = arrayListClass.getDeclaredField("elementData");
            field.setAccessible(true);
            Object[] objects = (Object[])field.get(arrayList);
            return objects.length;
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
            return -1;
        }
    }

}
