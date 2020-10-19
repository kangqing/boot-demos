package com.yunqing.demoatest.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.SortedMap;

/**
 * 一、通道：用于源节点与目标节点的连接。在Java NIO中负责缓冲区中数据的传输。
 * Channel本身不存储数据，因此需要配合缓冲区进行传输。
 *
 * 二、通道的主要实现类
 * java.nio.channels.Channel 接口：
 *     |--FileChannel           本地文件传出
 *     |--SocketChannel         套接字TCP网络传输
 *     |--ServerSocketChannel   套接字TCP网络传输
 *     |--DatagramChannel       UDP网络传输
 *
 * 三、获取通道
 * 1.Java针对支持通道的类提供了getChannel()方法
 *  本地IO:
 *  FileInputStream/FileOutputStream
 *  RandomAccessFile
 *
 *  网络IO：
 *  Socket
 *  ServerSocket
 *  DatagramSocket
 *
 *  2. 在JDK1.7中的NIO.2针对各个通道提供了静态方法open()
 *  3. 在JDK1.7中的NIO.2的Files工具类的newByteChannel()
 *  
 * @author yunqing
 * @since 2020/10/17 23:22
 */
public class ChannelTest {


    public static void main(String[] args) {
        test1();
    }



    /**
     * 字符集
     */
    private static void test5() throws CharacterCodingException {

        // 查看有多少个字符集
        SortedMap<String, Charset> map = Charset.availableCharsets();
        map.forEach((k, v) -> System.out.println("k = " + k + ", v = " + v));

        Charset gbk = Charset.forName("GBK");
        // 获取gbk的编码器
        CharsetEncoder charsetEncoder = gbk.newEncoder();
        // 获取gbk的解码器
        CharsetDecoder charsetDecoder = gbk.newDecoder();

        CharBuffer allocate = CharBuffer.allocate(1024);
        allocate.put("需要编解码的原始数据");
        allocate.flip();
        // 开始编码
        ByteBuffer encode = charsetEncoder.encode(allocate);
        // 开始解码
        allocate.flip();
        CharBuffer decode = charsetDecoder.decode(encode);
        // 输出经过gbk编解码后的数据
        System.out.println(decode.toString());
    }

    /**
     * // 分散与聚集
     * @throws IOException
     */
    private static void test4() throws IOException {
        RandomAccessFile file = new RandomAccessFile("1.jpg", "rw");
        // 1.获取通道
        FileChannel fileChannel = file.getChannel();
        // 2.分配指定大小的缓冲区
        ByteBuffer bb1 = ByteBuffer.allocate(100);
        ByteBuffer bb2 = ByteBuffer.allocate(1024);
        // 3.分散读取
        ByteBuffer[] bbs = {bb1, bb2};
        fileChannel.read(bbs);
        for (ByteBuffer bb : bbs) {
            bb.flip();
        }
        // 4. 聚集写入
        RandomAccessFile file2 = new RandomAccessFile("2.jpg","rw");
        FileChannel channel = file2.getChannel();
        channel.write(bbs);

        channel.close();
        fileChannel.close();
    }

    /**
     * // 通道之间的数据传输(直接缓冲区，不稳定)
     * @throws IOException
     */
    private static void test3() throws IOException {
        // 文件路径，读模式
        // 文件路径，读写模式，Create为存在则覆盖
        FileChannel inChannel = FileChannel.open(Paths.get("2.jpg"), StandardOpenOption.READ);
        FileChannel outChannel = FileChannel.open(Paths.get("3.jpg"), StandardOpenOption.WRITE,
                StandardOpenOption.READ, StandardOpenOption.CREATE);
        // 从inChannel到outChannel去
        inChannel.transferTo(0, inChannel.size(), outChannel);
        // 或者如下写法
        // outChannel.transferFrom(inChannel, 0, inChannel.size());
        inChannel.close();
        outChannel.close();
    }

    /**
     * / 使用内存映射文件，（直接缓冲区，不稳定）
     * @throws IOException
     */
    private static void test2() throws IOException {
        // 文件路径，读模式
        // 文件路径，读写模式，Create为存在则覆盖
        FileChannel inChannel = FileChannel.open(Paths.get("2.jpg"), StandardOpenOption.READ);
        FileChannel outChannel = FileChannel.open(Paths.get("3.jpg"), StandardOpenOption.WRITE,
                StandardOpenOption.READ, StandardOpenOption.CREATE);
        // 内存映射文件
        MappedByteBuffer inBuffer = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, inChannel.size());
        MappedByteBuffer outBuffer = outChannel.map(FileChannel.MapMode.READ_WRITE, 0, inChannel.size());

        // 直接对缓冲区进行数据的读写操作
        byte[] bytes = new byte[inBuffer.limit()];
        inBuffer.get(bytes);
        outBuffer.put(bytes);

        inChannel.close();
        outChannel.close();
    }

    //1、利用通道完成文件的复制(非直接缓冲区)
    private static void test1() {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            fis = new FileInputStream("1.jpg");
            fos = new FileOutputStream("2.jpg");
            //1.获取输入、输出通道
            FileChannel inChannel = fis.getChannel();
            FileChannel outChannel = fos.getChannel();
            //2.建立一个指定大小的缓冲区
            ByteBuffer bb = ByteBuffer.allocate(1024);
            //3.将通道中的数据存入缓冲区中
            while (inChannel.read(bb) != -1) {
                //切换成读数据模式
                bb.flip();
                //4、将缓冲区中的数据写入通道
                outChannel.write(bb);
                //清空缓冲区
                bb.clear();
            }
            outChannel.close();
            inChannel.close();
            fos.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
