package behavioral.chain;

import java.util.HashMap;
import java.util.Map;

/**
 * @author kangqing
 * @since 2023/4/20 07:51
 */
public class Server {
    private Map<String, String> users = new HashMap<>();
    private Middleware middleware;

    /**
     * 客户端将对象链传递给服务器。这提高了灵活性,使测试服务器类变得更容易。
     */
    public void setMiddleware(Middleware middleware) {
        this.middleware = middleware;
    }

    /**
     * 服务器从客户端获取电子邮件和密码并发送授权给请求链
     */
    public boolean logIn(String email, String password) {
        if (middleware.check(email, password)) {
            System.out.println("Authorization have been successful!");

            // Do something useful here for authorized users.

            return true;
        }
        return false;
    }

    public void register(String email, String password) {
        users.put(email, password);
    }

    public boolean hasEmail(String email) {
        return users.containsKey(email);
    }

    public boolean isValidPassword(String email, String password) {
        return users.get(email).equals(password);
    }
}
