package behavioral.template;

/**
 * 社交软件，抖音
 * @author kangqing
 * @since 2023/4/19 07:19
 */
public class Tiktok extends Network{
    @Override
    void login(String username) {
        System.out.println(username + "登陆了抖音");
    }

    @Override
    void sendMessage(String msg) {
        System.out.println("抖音发送了消息 = " + msg);
    }
}
