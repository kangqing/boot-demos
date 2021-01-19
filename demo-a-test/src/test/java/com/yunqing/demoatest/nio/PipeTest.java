package com.yunqing.demoatest.nio;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;

/**
 * 管道
 * @author kangqing
 * @since 2020/10/19 17:31
 */
@SpringBootTest
public class PipeTest {
    @Test
    void test() throws IOException {
        //获取管道
        Pipe pipe = Pipe.open();
        //分配指定数据大小的缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        //缓冲区写入数据
        byteBuffer.put("123456".getBytes());
        //缓冲区中数据写入管道
        Pipe.SinkChannel sink = pipe.sink();
        byteBuffer.flip();
        sink.write(byteBuffer);

        //读取缓冲区中的数据
        Pipe.SourceChannel source = pipe.source();
        byteBuffer.flip();
        int len = source.read(byteBuffer);
        System.out.println(new String(byteBuffer.array(), 0, len));

        source.close();
        sink.close();

    }
}
