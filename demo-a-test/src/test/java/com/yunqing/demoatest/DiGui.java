package com.yunqing.demoatest;

/**
 * @author yx
 * @since 2020/11/20 10:08
 */
public class DiGui {

    static IntCall fact;

    //Fibonacci 序列中的最后两个元素求和来产生下一个元素
    DiGui() {
        fact = n -> n == 0 ? 0 :
                    n == 1 ? 1 :
                            fact.call(n - 1) + fact.call(n - 2);
    }

    int fibonacci(int n) { return fact.call(n); }

    public static void main(String[] args) {
        // 阶乘是最经典的递归
        fact = n -> n == 0 ? 1 : n * fact.call(n - 1);
        System.out.println(fact.call(10));

        // 求Fibonacci序列第10个数
        DiGui diGui = new DiGui();
        int i = 10;
        System.out.println(diGui.fibonacci(i));

    }
}

/**
 * 函数式接口
 */
@FunctionalInterface
interface IntCall {
    int call(int arg);
}
