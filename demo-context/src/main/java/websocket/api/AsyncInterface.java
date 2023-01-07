package websocket.api;

import org.springframework.web.reactive.socket.WebSocketSession;
import websocket.AsyncPayload;
import websocket.AsyncTransportSender;

/**
 * 异步传输接口，实现websocket 的异步通讯
 * @author kangqing
 * @since 2022/12/31 16:26
 */
public interface AsyncInterface {

    /**
     * 通过websocketSession 实现数据的上传下载
     * @param session
     * @param message
     */
    default void sendMessage(WebSocketSession session, Object message) {
        AsyncTransportSender.sendMessage(session, message, getType());
    }

    /**
     * 从会话中接收数据
     * @param session
     * @param payload
     */
    void receive(WebSocketSession session, AsyncPayload payload);

    String getType();
}
