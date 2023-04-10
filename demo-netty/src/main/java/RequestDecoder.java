import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;
import model.RequestData;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * 请求解码器
 * 这个解码器的想法非常简单。它使用ByteBuf的实现，当缓冲区中没有足够的数据用于读取操作时抛出异常。
 *
 * 当捕获到异常时，缓冲区将倒回到开头，解码器等待新的数据部分。解码执行后out列表不为空时解码停止。
 * @author kangqing
 * @since 2023/4/2 17:18
 */
public class RequestDecoder extends ReplayingDecoder<RequestData> {

    private final Charset charset = StandardCharsets.UTF_8;

    @Override
    protected void decode(ChannelHandlerContext ctx,
                          ByteBuf in, List<Object> out) throws Exception {
        // 3. 服务端接收到请求解码
        RequestData data = new RequestData();
        data.setIntValue(in.readInt());
        if (in.writerIndex() - in.readerIndex() != 0) {
            int strLen = in.readInt();
            data.setStringValue(
                    in.readCharSequence(strLen, charset).toString());
        }
        out.add(data);
    }
}