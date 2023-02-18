package com.kangqing;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;

/**
 * @author kangqing
 * @since 2023/2/7 07:51
 */
@SpringBootTest
public class DemoSpringApplicationTest {

    @Autowired
    private CommandManager manager;
    @Autowired
    private CommandManagerLookup commandManagerLookup;


    @Test
    void test() {
        final HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("a", "1");
        final Object process = commandManagerLookup.process(objectObjectHashMap);
        System.out.println(process);
    }
}
