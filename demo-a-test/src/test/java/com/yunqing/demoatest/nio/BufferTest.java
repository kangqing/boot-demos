package com.yunqing.demoatest.nio;

import java.nio.ByteBuffer;

/**
 * 缓冲区：在Java NIO中负责数据的存取，底层是数组，用于存取不同数据类型的数据
 * 根据数据类型不同（boolean类型除外），提供了相应类型的缓冲区：
 * ByteBuffer  最常用的
 * CharBuffer
 * ShortBuffer
 * IntBuffer
 * LongBuffer
 * FloatBuffer
 * DoubleBuffer
 *
 * 上述缓冲区的管理方式几乎一致，通过 allocate()获取缓冲区
 *
 * 二。缓冲器存取数据的两个核心方法
 * put() 存入缓冲区
 * get() 获取缓冲区中的数据
 *
 * 三。缓冲区中的四个核心属性
 * capacity：容量，缓冲区中最大存储数据的容量，一旦声明不能改变
 * limit: 界限，表示缓冲区中可以操作数据的大小（limit后面的数据不能进行读写）
 * position: 位置，表示缓冲区中正在操作数据的位置。
 * 规律 ： 0 <= mark <= position <= limit <= capacity
 * mark：标记，表示记录当前position的位置。可通过reset()恢复到mark记录的位置
 *
 *
 * 判断缓冲区中是否还有可以操作的数据
 * if (bb.hasRemaining()) {
 *     //有几个可操作的数据
 *     bb.remaining();
 * }
 *
 * 四、直接缓冲区与非直接缓冲区
 * 非直接缓冲区：通过allocate()方法分配的缓冲区，将缓冲区建立在JVM内存中
 * 直接缓冲区：通过allocateDirect()方法分配的直接缓冲区，将缓冲器官建立在物理内存上，可以提高效率
 *
 * @author yunqing
 * @since 2020/10/17 9:10
 */
public class BufferTest {

    public static void main(String[] args) {
        // 建立一个指定大小的缓冲区
        ByteBuffer bb = ByteBuffer.allocate(1024);
        System.out.println("-------------allocate(1024)分配指定缓冲区之后----------------");
        System.out.println("当前位置是 ： " + bb.position());
        System.out.println("当前界限是 ： " + bb.limit());
        System.out.println("当前容量是 ： " + bb.capacity());

        // 存储数据,占用了5个字节
        bb.put("abcde".getBytes());
        System.out.println("-------------put()存入数据到缓冲区之后----------------");
        System.out.println("当前位置是 ： " + bb.position());
        System.out.println("当前界限是 ： " + bb.limit());
        System.out.println("当前容量是 ： " + bb.capacity());

        // 切换到读数据模式
        bb.flip();
        System.out.println("-------------flip()切换到读数据模式之后----------------");
        System.out.println("当前位置是 ： " + bb.position());
        System.out.println("当前界限是 ： " + bb.limit());
        System.out.println("当前容量是 ： " + bb.capacity());

        // get()获取缓冲区中数据
        byte[] bytes = new byte[bb.limit()];
        bb.get(bytes);
        System.out.println(new String(bytes, 0, bytes.length));
        System.out.println("-------------get()之后----------------");
        System.out.println("当前位置是 ： " + bb.position());
        System.out.println("当前界限是 ： " + bb.limit());
        System.out.println("当前容量是 ： " + bb.capacity());

        // 可重复读数据，position重置回0位置索引
        bb.rewind();
        System.out.println("-------------rewind()之后----------------");
        System.out.println("当前位置是 ： " + bb.position());
        System.out.println("当前界限是 ： " + bb.limit());
        System.out.println("当前容量是 ： " + bb.capacity());

        // 清空缓冲区，但是缓冲区中的数据依然存在，但是处于被遗忘状态
        bb.clear();
        System.out.println("-------------clear()之后----------------");
        System.out.println("当前位置是 ： " + bb.position());
        System.out.println("当前界限是 ： " + bb.limit());
        System.out.println("当前容量是 ： " + bb.capacity());

        // 证明数据依然存在
        System.out.println((char)bb.get());
    }
}
