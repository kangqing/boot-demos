package com.yunqing.demoatest.readjdk.java.util.function;

import java.util.function.Consumer;

/**
 * 四大函数式接口之  消费型接口
 * @author kangqing
 * @since 2021/3/21 15:03
 */
public class ConsumerTest {
    public static void main(String[] args) {
        /**
         * 接收一个参数没有返回值
         */
        /*Consumer<String> stringConsumer = new Consumer<>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        };*/

        /**
         * lambda 表达式简写
         */
        //Consumer<String> stringConsumer = str -> System.out.println(str);
        // 方法引用简写
        Consumer<String> stringConsumer = System.out::println;

        stringConsumer.accept("111");
    }
}
