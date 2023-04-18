package behavioral.template;

/**
 * 社交软件
 * @author kangqing
 * @since 2023/4/19 07:14
 */
public abstract class Network {

    /**
     * 发送消息
     * @return
     */
    public void send(String username, String msg) {
        // 登录
        login(username);
        // 发送消息
        sendMessage(msg);
    }

    abstract void login(String username);
    abstract void sendMessage(String msg);

}
