package buf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.embedded.EmbeddedChannel;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * @author kangqing
 * @since 2023/6/1 21:03
 */
@Slf4j
public class AllocatorTest {

    // 输出 ByteBuf 是否时直接内存或者堆内存
    private static void printByteBuf(String action, ByteBuf buf) {
        log.info("======" + action + "======");
        // true = 表示缓冲区是堆内存（组合缓冲区例外）
        // false = 表示缓冲区是操作系统直接内存（组合缓冲区例外）
        log.info("buf.hasArray() = " + buf.hasArray());
        // 输出内存分配器
        log.info("内存分配器是 = " + buf.alloc());
    }

    // 处理器类
    static class AllocDemoHandler extends ChannelInboundHandlerAdapter {
        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            printByteBuf("入站的 ByteBuf", (ByteBuf)msg);
            // 推荐使用的方式
            ByteBuf buf = ctx.alloc().buffer();
            buf.writeInt(100);
            // 向模拟通道写入一个出站包，模拟数据出站，需要刷新才能获取到输出
            ctx.channel().writeAndFlush(buf);
        }
    }

    @Test
    public void test() {
        ChannelInitializer<EmbeddedChannel> i = new ChannelInitializer<>() {
            @Override
            protected void initChannel(EmbeddedChannel ch) throws Exception {
                ch.pipeline().addLast(new AllocDemoHandler());
            }
        };

        final EmbeddedChannel channel = new EmbeddedChannel(i);
        // 可以在 BootStrap 或者在此设置缓冲区分配器，这里设置一个池化的分配器
        channel.config().setAllocator(PooledByteBufAllocator.DEFAULT);
        ByteBuf buf = Unpooled.buffer();
        buf.writeInt(1);
        // 向模拟通道写入一个入站包，模拟数据入站
        channel.writeInbound(buf);
        // 获取通道的出站包
        ByteBuf outBuf = channel.readOutbound();
        printByteBuf("出站的ByteBuf = ", outBuf);
    }
}
