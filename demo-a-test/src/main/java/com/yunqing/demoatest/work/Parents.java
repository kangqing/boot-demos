package com.yunqing.demoatest.work;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author kangqing
 * @since 2024/7/16 13:16
 */
@Data
public class Parents {

    protected static Map<String, Object> map = new HashMap<>();

}

class Child extends Parents {

    public void test() {
        System.out.println(map.get("aaa"));
    }


    public static void main(String[] args) {
        map.put("aaa", "111");
        final Child child = new Child();
        child.test();
    }
}
