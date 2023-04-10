import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import model.ResponseData;

import java.nio.charset.StandardCharsets;

/**
 * 除了解码RequestData之外，我们还需要对消息进行编码。此操作更简单，因为我们在写操作发生时拥有完整的消息数据。
 *
 * 我们可以在主处理程序中将数据写入Channel，或者我们可以分离逻辑并创建一个扩展MessageToByteEncoder的处理程序，它将捕获写入ResponseData操作：
 * @author kangqing
 * @since 2023/4/2 17:20
 */
public class ResponseDataEncoder
        extends MessageToByteEncoder<ResponseData> {

    @Override
    protected void encode(ChannelHandlerContext ctx,
                          ResponseData msg, ByteBuf out) throws Exception {
        // 5.服务端处理后编码
        out.writeInt(msg.getIntValue());
        if (msg.getXxx() != null) {
            out.writeInt(msg.getXxx().length());
            out.writeCharSequence(msg.getXxx(), StandardCharsets.UTF_8);
        }

    }
}
