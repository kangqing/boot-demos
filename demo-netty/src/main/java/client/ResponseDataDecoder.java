package client;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;
import model.ResponseData;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * 客户端应该执行反向编码和解码，所以我们需要有一个RequestDataEncoder和ResponseDataDecoder：
 * @author kangqing
 * @since 2023/4/2 17:28
 */
public class ResponseDataDecoder
        extends ReplayingDecoder<ResponseData> {

    @Override
    protected void decode(ChannelHandlerContext ctx,
                          ByteBuf in, List<Object> out) throws Exception {
        // 6. 客户端接收到相应后解码
        ResponseData data = new ResponseData();
        data.setIntValue(in.readInt());
        if (in.writerIndex() - in.readerIndex() != 0) {
            int len = in.readInt();
            final CharSequence charSequence = in.readCharSequence(len, StandardCharsets.UTF_8);
            data.setXxx(charSequence.toString());
        }

        out.add(data);
    }
}
