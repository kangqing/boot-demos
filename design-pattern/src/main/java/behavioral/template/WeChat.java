package behavioral.template;

/**
 * 社交软件，微信
 * @author kangqing
 * @since 2023/4/19 07:16
 */
public class WeChat extends Network{
    @Override
    void login(String username) {
        System.out.println(username + "登陆了微信");
    }

    @Override
    void sendMessage(String msg) {
        System.out.println("微信发送了消息 = " + msg);
    }
}
