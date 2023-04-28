package client;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import model.RequestData;
import model.ResponseData;

/**
 * 我们需要定义一个ClientHandler，它将发送请求并从服务器接收响应：
 * @author kangqing
 * @since 2023/4/2 17:29
 */
public class ClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // 1. 封装请求
        RequestData msg = new RequestData();
        msg.setIntValue(123);
        //msg.setStringValue("all work and no play makes jack a dull boy");
        ChannelFuture future = ctx.writeAndFlush(msg);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // 7. 处理响应数据
        System.out.println((ResponseData)msg);
        ctx.close();
    }
}
