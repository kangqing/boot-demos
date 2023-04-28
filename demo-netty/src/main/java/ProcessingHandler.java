import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;
import model.RequestData;
import model.ResponseData;

/**
 * 由于我们在单独的处理程序中执行解码和编码，因此我们需要更改ProcessingHandler：
 * @author kangqing
 * @since 2023/4/2 17:21
 */
@Slf4j
public class ProcessingHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {
        // 4.服务端处理数据
        RequestData requestData = (RequestData) msg;
        ResponseData responseData = new ResponseData();
        responseData.setIntValue(requestData.getIntValue() * 2);
        responseData.setXxx(requestData.getStringValue());
        ChannelFuture future = ctx.writeAndFlush(responseData);
        future.addListener(ChannelFutureListener.CLOSE);
        log.info("接收到入站channel-->handler操作，数据 = {}", requestData);
    }
}
