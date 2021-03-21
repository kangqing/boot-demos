package com.yunqing.demoatest.readjdk.java.util.function;

import java.util.function.Supplier;

/**
 * 四大函数式接口之 供给型接口
 * @author kangqing
 * @since 2021/3/21 15:07
 */
public class SupplierTest {
    public static void main(String[] args) {
        /**
         * 没有参数，只有一个返回值
         */
        /*Supplier<String> stringSupplier = new Supplier<>() {
            @Override
            public String get() {
                return "666";
            }
        };*/
        /**
         * lambda 表达式简写
         */
        Supplier<String> stringSupplier = () -> "6666";
        System.out.println(stringSupplier.get());
    }
}
