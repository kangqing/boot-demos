import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;

/**
 * @author kangqing
 * @since 2023/4/2 17:22
 */
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
public class NettyServer {

    private int port;

    // constructor

    public static void main(String[] args) throws Exception {

        new NettyServer(8080).run();
    }

    public void run() throws Exception {
        /**
         * 1. 创建反应器轮询组
         */
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup);
            // 2. 设置通道 IO 类型
            b.channel(NioServerSocketChannel.class);
            // 3. 设置监听端口
            b.localAddress(new InetSocketAddress(port));
            // 4. 设置父子通道的配置选项
            b.option(ChannelOption.SO_BACKLOG, 128); // 表示服务端接收链接的队列长度，如果队列已满，客户端连接将被拒绝
            b.childOption(ChannelOption.SO_KEEPALIVE, true); // 子通道连接保持心跳
            // 5. 装配子通道管道
            b.childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(
                            new RequestDecoder(),
                            new ResponseDataEncoder(),
                            new ProcessingHandler());
                }
            });
            // 6. 开始绑定端口
            ChannelFuture f = b.bind().sync();
            //ChannelFuture f = b.bind(port).sync();
            log.info("服务器启动成功，监听端口 = {}", f.channel().localAddress());
            // 7. 自我阻塞，直到监听通道关闭
            f.channel().closeFuture().sync();
        } finally {
            // 8. 关闭反应器轮询组
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }
}
