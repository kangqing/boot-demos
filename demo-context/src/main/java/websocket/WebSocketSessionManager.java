package websocket;

import org.springframework.web.reactive.socket.WebSocketSession;

import java.util.HashMap;
import java.util.Map;

/**
 * @author kangqing
 * @since 2022/12/31 16:22
 */
public class WebSocketSessionManager {

    public static final Map<String, WebSocketSession> SESSION_MAP = new HashMap<>();

    public static void add(AsyncPayload asyncPayload, WebSocketSession session) {
        SESSION_MAP.put(asyncPayload.getBeanName(), session);
    }

    public static WebSocketSession get(String token) {
        final WebSocketSession session = SESSION_MAP.get(token);
        if (session != null) {
            return session;
        }
        throw new IllegalArgumentException("Not found web socket session by token " + token);
    }
}
