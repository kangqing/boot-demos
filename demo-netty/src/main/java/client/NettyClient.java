package client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 *
 * @author kangqing
 * @since 2023/4/2 17:30
 */
public class NettyClient {
    public static void main(String[] args) throws Exception {

        String host = "localhost";
        int port = 8080;

        /**
         * // 反应器轮询组
         * // boss:负责连接和监听IO事件
         * // worker:负责传输事件和处理
         * // 风险点：新连接的接收可能被更加耗时的数据传输或者业务处理阻塞，在服务端，建议设置成两个轮询组，这里客户端可以这样用
         * 1. 创建反应器轮询组
         */
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            Bootstrap b = new Bootstrap();
            b.group(workerGroup);
            /**
             * 2.设置通道的 IO 类型
             * 3.监听端口和第 6 步骤写在一起了
             */
            b.channel(NioSocketChannel.class);
            // 4. 设置通道选项
            b.option(ChannelOption.SO_KEEPALIVE, true);
            // 5. 装配子通道的管道
            b.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new RequestDataEncoder(),
                            new ResponseDataDecoder(), new ClientHandler());
                }
            });
            // 6. 开始绑定服务器新连接的监听端口
            ChannelFuture f = b.connect(host, port).sync();
            // 7. 自我阻塞，直到监听通道关闭
            f.channel().closeFuture().sync();
        } finally {
            // // 8. 关闭反应器轮询组
            workerGroup.shutdownGracefully();
        }
    }
}
