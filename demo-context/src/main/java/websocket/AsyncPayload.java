package websocket;

import lombok.Data;

/**
 * @author kangqing
 * @since 2022/12/31 16:15
 */
@Data
public class AsyncPayload {

    private String token;

    private String beanName;
    // json格式
    private Object data;

    public AsyncPayload(String beanName, Object data) {
        this.beanName = beanName;
        this.data = data;
    }
}
