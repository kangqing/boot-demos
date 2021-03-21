package com.yunqing.demoatest.readjdk.java.util.function;

import java.util.function.Predicate;

/**
 * 四大函数式接口 之  断定型接口
 * @author kangqing
 * @since 2021/3/21 14:58
 */
public class PredicateTest {
    public static void main(String[] args) {
        /**
         * 一个参数，返回值默认是布尔类型
         */
        /*Predicate<String> stringPredicate = new Predicate<>() {
            @Override
            public boolean test(String s) {
                return s.isEmpty();
            }
        };*/

        /**
         * lambda 表达式简写
         */
        //Predicate<String> stringPredicate = str -> str.isEmpty();
        // 方法引用简写
        Predicate<String> stringPredicate = String::isEmpty;

        System.out.println(stringPredicate.test("000"));
    }
}
