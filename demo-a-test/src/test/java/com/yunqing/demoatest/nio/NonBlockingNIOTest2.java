package com.yunqing.demoatest.nio;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;

/**
 * 一、使用非阻塞式NIO完成网络通信的核心
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
 *
 *  # 解决springbootTest中不能Scanner键盘输入问题，在VM中加入
 *  -Deditable.java.test.console=true
 * @author kangqing
 * @since 2020/10/19 14:40
 */
@SpringBootTest
public class NonBlockingNIOTest2 {

    /**
     * 客户端
     */
    @Test
    void send() throws IOException {
        //1、获取通道
        DatagramChannel datagramChannel = DatagramChannel.open();
        //2、 切换成非阻塞模式
        datagramChannel.configureBlocking(false);

        //3、分配至定大小的缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        //4、发送数据给服务端
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {
            String str = scanner.next();
            byteBuffer.put((LocalDateTime.now().toString() + "\n" + str).getBytes());
            byteBuffer.flip();
            datagramChannel.send(byteBuffer, new InetSocketAddress("127.0.0.1", 9898));
            byteBuffer.clear();
        }
        //4、关闭通道
        datagramChannel.close();
    }

    /**
     * 服务端
     */
    @Test
    void receive() throws IOException {
        //1、获取通道
        DatagramChannel datagramChannel = DatagramChannel.open();
        //2、切换成非阻塞模式
        datagramChannel.configureBlocking(false);
        //3、绑定连接
        datagramChannel.bind(new InetSocketAddress(9898));

        // 3、获取选择器
        Selector selector = Selector.open();

        //4、将通道注册到选择器上,并指定监听“读事件”
        datagramChannel.register(selector, SelectionKey.OP_READ);
        //6.轮询获取选择器上已准备就绪的事件
        while (selector.select() > 0) {
            //获取当前前选择器中所有注册的选择键（已就绪的监听事件）
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            //迭代获取准备就绪的事件
            while (iterator.hasNext()) {
                SelectionKey sk = iterator.next();
                //判断具体是什么事件准备就绪
                if (sk.isReadable()) {
                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                    datagramChannel.receive(byteBuffer);
                    byteBuffer.flip();
                    System.out.println(new String(byteBuffer.array(), 0, byteBuffer.limit()));
                    byteBuffer.clear();
                }
                //取消选择键
                iterator.remove();
            }
        }


        //6、关闭对应通道
        datagramChannel.close();
    }
}
