package com.yunqing.demoatest.bilibili.io;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * blocking io 阻塞式 io
 * @author kangqing
 * @since 2021/6/30 21:27
 */
public class Bio {
    public static void main(String[] args) throws IOException {
        // 在 9000 端口建立socket服务端
        ServerSocket serverSocket = new ServerSocket(9000);
        while (true) {
            System.out.println("等待客户端连接到9000端口....");
            // 阻塞方法
            Socket clientSocket = serverSocket.accept();
            System.out.println("有客户端连接到9000端口...");

            /**
             * //假想优化，可以声明新的线程
             * //分析：
             *  会出现 c10k(连接10000个客户端)
             *  c10m(连接10000000个客户端) 的问题
             *  如果使用线程池，又会把并发量限制在线程池最大线程数，所以 BIO 不是一个好的解决方案
             */
            new Thread(() -> {
                try {
                    handler(clientSocket);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();

        }
    }

    /**
     * 连接之后的操作
     * @param clientSocket socket 客户端
     */
    private static void handler(Socket clientSocket) throws IOException {
        // 字节数组，用来存储客户端发过来的数据
        byte[] bytes = new byte[1024];
        System.out.println("准备读取数据...");
        // 用于接收客户端的数据，没有可接受的数据就阻塞在这
        int read = clientSocket.getInputStream().read(bytes);
        System.out.println("read完毕...");
        if (read != -1) {
            System.out.println("接收到客户端传输的数据是" + new String(bytes, 0, read));
        }
        clientSocket.getOutputStream().write("输出在客户端".getBytes(StandardCharsets.UTF_8));
        clientSocket.getOutputStream().flush();
    }
}
