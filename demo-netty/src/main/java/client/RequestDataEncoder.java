package client;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import model.RequestData;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * 客户端应该执行反向编码和解码，所以我们需要有一个RequestDataEncoder和ResponseDataDecoder：
 * @author kangqing
 * @since 2023/4/2 17:27
 */
public class RequestDataEncoder
        extends MessageToByteEncoder<RequestData> {

    private final Charset charset = StandardCharsets.UTF_8;

    @Override
    protected void encode(ChannelHandlerContext ctx,
                          RequestData msg, ByteBuf out) throws Exception {
        // 2. 请求编码
        out.writeInt(msg.getIntValue());
        if (msg.getStringValue() != null) {
            out.writeInt(msg.getStringValue().length());
            out.writeCharSequence(msg.getStringValue(), charset);
        }

    }
}
