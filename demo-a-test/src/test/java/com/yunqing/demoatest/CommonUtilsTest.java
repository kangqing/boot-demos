package com.yunqing.demoatest;

import com.yunqing.demoatest.utils.CommonUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
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
}
