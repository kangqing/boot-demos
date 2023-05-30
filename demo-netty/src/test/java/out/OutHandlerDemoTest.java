package out;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.jupiter.api.Test;

/**
 * @author kangqing
 * @since 2023/5/29 20:43
 */
public class OutHandlerDemoTest {

    @Test
    public void test() {
        final OutHandlerDemo outHandler = new OutHandlerDemo();
        ChannelInitializer i = new ChannelInitializer<EmbeddedChannel>() {
            protected void initChannel(EmbeddedChannel ch) {
                ch.pipeline().addLast(outHandler);
            }
        };

        EmbeddedChannel channel = new EmbeddedChannel(i);

//        channel.pipeline().addLast(handler);

        //测试出站写入

        ByteBuf buf = Unpooled.buffer();
        buf.writeInt(1);

//        ChannelFuture f = channel.writeAndFlush(buf);
        ChannelFuture f = channel.pipeline().writeAndFlush(buf);

        f.addListener((future) -> {
            if (future.isSuccess()) {
                System.out.println("write is finished");
            }
            channel.close();
        });

//        try {
//            Thread.sleep(Integer.MAX_VALUE);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

    }

}
