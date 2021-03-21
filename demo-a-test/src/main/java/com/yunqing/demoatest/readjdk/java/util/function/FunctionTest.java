package com.yunqing.demoatest.readjdk.java.util.function;

import java.util.function.Function;

/**
 * 四大函数式接口之  函数型接口
 * @author kangqing
 * @since 2021/3/21 14:53
 */
public class FunctionTest {
    public static void main(String[] args) {
        /**
         * 一个参数一个返回值
         */
        /*Function<String, String> stringStringFunction = new Function<String, String>() {
            @Override
            public String apply(String s) {
                return s + "666";
            }
        };*/

        /**
         * lambda 表达式简写
         */
        Function<String, String> stringStringFunction = str -> str + "666";

        System.out.println(stringStringFunction.apply("kangqing"));
    }
}
