package behavioral.chain;

/**
 * 检查是否存在具有给定凭据的用户
 * @author kangqing
 * @since 2023/4/20 07:50
 */
public class UserExistsMiddleware extends Middleware{
    private Server server;

    public UserExistsMiddleware(Server server) {
        this.server = server;
    }

    public boolean check(String email, String password) {
        if (!server.hasEmail(email)) {
            System.out.println("This email is not registered!");
            return false;
        }
        if (!server.isValidPassword(email, password)) {
            System.out.println("Wrong password!");
            return false;
        }
        return checkNext(email, password);
    }
}

