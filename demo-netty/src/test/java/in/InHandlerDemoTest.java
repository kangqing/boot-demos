package in;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.jupiter.api.Test;

/**
 * @author kangqing
 * @since 2023/5/29 20:28
 */
public class InHandlerDemoTest {
    
    @Test
    public void test() {
        final InHandlerDemo inHandlerDemo = new InHandlerDemo();
        final ChannelInitializer<EmbeddedChannel> i = new ChannelInitializer<>() {

            @Override
            protected void initChannel(EmbeddedChannel ch) throws Exception {
                ch.pipeline().addLast(inHandlerDemo);
            }
        };
        // 创建嵌入式通道
        final EmbeddedChannel embeddedChannel = new EmbeddedChannel(i);
        ByteBuf buf = Unpooled.buffer();
        buf.writeInt(1);
        // 模拟入站，向嵌入式通道写一个入站数据包
        embeddedChannel.writeInbound(buf);
        embeddedChannel.flush();
        // 模拟入站，再写一个数据包
        embeddedChannel.writeInbound(buf);
        embeddedChannel.flush();
        // 通道关闭
        embeddedChannel.close();
    }
}
