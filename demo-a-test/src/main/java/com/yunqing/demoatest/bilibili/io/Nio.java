package com.yunqing.demoatest.bilibili.io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Non io 非阻塞 io
 * @author kangqing
 * @since 2021/7/1 19:30
 */
public class Nio {
    //保存客户端连接
    static List<SocketChannel> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        // 创建 NIO ServerSocketChannel
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        // 绑定9000端口
        serverSocketChannel.socket().bind(new InetSocketAddress(9000));
        // 设置serverSocketChannel为非阻塞
        serverSocketChannel.configureBlocking(false);
        System.out.println("服务启动成功，等待客户端连接");

        while (true) {
            //非阻塞模式 accept() 不会阻塞，否则会阻塞
            // NIO 的非阻塞是由操作系统内部实现的，底层调用了 linux 内核的 accept函数
            SocketChannel channel = serverSocketChannel.accept();
            // 如果channel不为空，则有客户端进行连接
            if (channel != null) {
                System.out.println("客户端连接成功");
                // 设置channel为非阻塞
                channel.configureBlocking(false);
                // 保存客户端连接到List中
                list.add(channel);
            }
            /**
             * 待优化：10万个连接，只有几个在交互，也要全部遍历
             */
            // 遍历客户端连接进行读取
            Iterator<SocketChannel> iterator = list.iterator();
            while (iterator.hasNext()) {
                SocketChannel next = iterator.next();
                ByteBuffer buffer = ByteBuffer.allocate(128);
                // 非阻塞模式 read() 不会阻塞
                int len = next.read(buffer);
                // 如果有数据打印出来
                if (len > 0) {
                    System.out.println("接收到的消息是" + new String(buffer.array()));
                } else if (len == -1){ //如果客户端断开，则从list中移除
                    iterator.remove();
                    System.out.println("客户端断开连接");
                }

            }
        }
    }
}
