package com.yunqing.demoatest.readjdk.java.util;

import cn.hutool.core.lang.Console;

import java.util.BitSet;

/**
 * BitSet类实现一个按需增长的位向量
 * 线程不安全
 * 集合中所有位的初始值是false
 * 传递一个 null 参数到BitSet中的任何方法都会空指针
 * @author kangqing
 * @since 2020/9/22 17:46
 */
public class BitSetTest {
    public static void main(String[] args) {
        BitSet bitSet = new BitSet();
        BitSet bitSet1 = new BitSet(10);
        Console.log("bitset中默认值是 {}, bitset的默认长度是 {}, 指定长度为10之后长度 {}", bitSet.get(0),
                bitSet.length(), bitSet1.length());

        bitSet.set(0);
        bitSet.set(1);
        bitSet.set(2);
        Console.log("输出bitSet = {}, 长度为 {}", bitSet, bitSet.length());
        bitSet1.set(1, 5);
        Console.log("输出bitSet1 = {}, 长度为 {}", bitSet1, bitSet1.length());

    }
}
