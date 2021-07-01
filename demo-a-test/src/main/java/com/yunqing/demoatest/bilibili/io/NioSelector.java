package com.yunqing.demoatest.bilibili.io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * NIO 多路复用器
 * @author kangqing
 * @since 2021/7/1 21:39
 */
public class NioSelector {
    public static void main(String[] args) throws IOException {
        // 创建 NIO ServerSocketChannel
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        // 绑定9000端口
        serverSocketChannel.socket().bind(new InetSocketAddress(9000));
        // 设置serverSocketChannel为非阻塞
        serverSocketChannel.configureBlocking(false);
        // 打开Selector处理channel, 即创建 epoll
        Selector epoll = Selector.open();
        // 把ServerSocketChannel注册到 epoll上， 并且epoll 对客户端的accept() 连接操作感兴趣
        SelectionKey selectionKey = serverSocketChannel.register(epoll, SelectionKey.OP_ACCEPT);
        System.out.println("服务启动成功，等待客户端连接...");

        while (true) {
            // 阻塞等待需要处理的事件发生
            epoll.select();
            // 获取 epoll 中注册的全部事件的 selectorKey 实例
            Set<SelectionKey> selectionKeys = epoll.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            if (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                //如果是OP_ACCEPT事件，则进行连接获取和事件注册
                if (key.isAcceptable()) {
                    ServerSocketChannel server = (ServerSocketChannel) key.channel();
                    try {
                        SocketChannel channel = server.accept();
                        channel.configureBlocking(false);
                        //这里只注册了读事件，如果需要给客户端发送数据可以注册写事件
                        SelectionKey readKey = channel.register(epoll, SelectionKey.OP_READ);
                        System.out.println("客户端连接成功...");
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                } else if (key.isReadable()) {
                    // 如果是 OP_READ 事件则进行读取和打印
                    SocketChannel channel = (SocketChannel) key.channel();
                    ByteBuffer byteBuffer = ByteBuffer.allocate(128);
                    try {
                        int len = channel.read(byteBuffer);
                        // 如果有数据就读取打印出来
                        if (len > 0) {
                            System.out.println("接收到的消息是" + new String(byteBuffer.array()));
                        } else if (len == -1){ //如果客户端断开,关闭socket
                            System.out.println("客户端断开连接");
                            channel.close();
                        }
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
                // 从事件集合删除本次处理的key防止你下次重复处理
                iterator.remove();
            }

        }
    }
}
