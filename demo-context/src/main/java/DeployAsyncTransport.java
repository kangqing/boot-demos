import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.socket.WebSocketSession;
import websocket.AsyncPayload;
import websocket.api.AsyncInterface;

/**
 * @author kangqing
 * @since 2022/12/31 16:54
 */
@Service(DeployAsyncTransport.BEAN_NAME)
public class DeployAsyncTransport implements AsyncInterface, ApplicationContextAware {
    static final String BEAN_NAME = "deploy";

    private ApplicationContext context;

    private final ThreadPoolTaskExecutor taskExecutor;

    public DeployAsyncTransport(ThreadPoolTaskExecutor executor) {
        this.taskExecutor = executor;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

    @Override
    public void receive(WebSocketSession session, AsyncPayload payload) {
        this.taskExecutor.execute(() -> asyncBuild(session, payload));
    }

    private void asyncBuild(WebSocketSession session, AsyncPayload payload) {
    }

    @Override
    public String getType() {
        return BEAN_NAME;
    }
}
