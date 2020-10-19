package com.yunqing.demoatest.nio;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * 一、使用阻塞式NIO完成网络通信的核心
 *  * 1.通道：Channel 负责连接
 *  *      java.nio.channels.Channel 接口：
 *  *          |--SelectableChannel
 *  *              |--SocketChannel
 *  *              |--ServerSocketChannel
 *  *              |--DatagramChannel
 *  *
 *  *              |--Pipe.SinkChannel
 *  *              |--Pipe.SourceChannel
 *  * 2.缓冲区：Buffer 负责存取数据
 *  * 3.选择器：Selector 是SelectableChannel的多路复用器，用于监控SelectableChannel的IO状况
 * @author yx
 * @since 2020/10/19 14:40
 */
@SpringBootTest
public class BlockingNIOTest {
    /**
     * 客户端
     */
    @Test
    void client() throws IOException {
        //1、获取通道
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9898));
        FileChannel fileChannel = FileChannel.open(Paths.get("1.jpg"), StandardOpenOption.READ);
        //2、分配至定大小的缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        //3、读取本地文件，并发送到服务端
        while (fileChannel.read(byteBuffer) != -1) {
            byteBuffer.flip();
            socketChannel.write(byteBuffer);
            byteBuffer.clear();
        }
        //4、关闭通道
        fileChannel.close();
        socketChannel.close();
    }

    /**
     * 服务端
     */
    @Test
    void server() throws IOException {
        //1、获取通道
        ServerSocketChannel s = ServerSocketChannel.open();

        FileChannel outChannel = FileChannel.open(Paths.get("2.jpg"), StandardOpenOption.WRITE, StandardOpenOption.CREATE);
        //2、绑定连接
        s.bind(new InetSocketAddress(9898));

        //3、获取客户端连接的通道
        SocketChannel socketChannel = s.accept();

        //4、分配一个指定大小的缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        //5、接收客户端的数据，并保存在本地
        while (socketChannel.read(byteBuffer) != -1) {
            byteBuffer.flip();
            outChannel.write(byteBuffer);
            byteBuffer.clear();
        }
        //6、关闭对应通道
        socketChannel.close();
        outChannel.close();
        s.close();
    }
}
