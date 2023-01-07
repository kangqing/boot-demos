package websocket;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Mono;

/**
 * 序列化
 * @author kangqing
 * @since 2022/12/31 16:33
 */
public class AsyncTransportSender {
    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    static {
        OBJECT_MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        OBJECT_MAPPER.configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true);
        OBJECT_MAPPER.configure(DeserializationFeature.READ_ENUMS_USING_TO_STRING, true);
        OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        // 去掉默认使用getter做序列化的设置
        OBJECT_MAPPER.setVisibilityChecker(
                OBJECT_MAPPER.getSerializationConfig().getDefaultVisibilityChecker()
                        .withFieldVisibility(JsonAutoDetect.Visibility.ANY)
                        .withGetterVisibility(JsonAutoDetect.Visibility.NONE));
    }

    public static String toJsonString(Object object) {
        try {
            return OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("write json String has error.", e);
        }
    }

    public static void sendMessage(WebSocketSession session, Object message, String type) {
        try {
            session.send(Mono.just(session.textMessage(toJsonString(new AsyncPayload(type, message))))).block();
        } catch (Exception ignored) {

        }

    }


}
