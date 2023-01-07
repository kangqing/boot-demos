package websocket;

import cn.hutool.json.JSONUtil;
import cn.hutool.log.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Mono;
import websocket.api.AsyncInterface;

/**
 * websocket
 * @author kangqing
 * @since 2022/12/31 16:02
 */
public class MyWebSocketHandler implements WebSocketHandler, ApplicationContextAware {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyWebSocketHandler.class);

    private ApplicationContext context;


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

    @Override
    public Mono<Void> handle(WebSocketSession session) {
        return session.receive()
                .doOnNext(message -> receive(session, message.getPayloadAsText()))
                .then();
    }

    private void receive(WebSocketSession session, String message) {
        AsyncPayload payload = JSONUtil.toBean(message, AsyncPayload.class);
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(message);
        }
        try {
            this.context.getBean(payload.getBeanName(), AsyncInterface.class)
                    .receive(session, payload);
            WebSocketSessionManager.add(payload, session);
        } catch (BeansException e) {
            LOGGER.error("websocket未获取到对应的bean" + payload.getBeanName());
        } catch (Throwable t) {
            LOGGER.error("执行异步逻辑出错", t);
        }
    }


}
