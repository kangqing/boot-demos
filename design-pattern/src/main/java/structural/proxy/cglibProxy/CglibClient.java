package structural.proxy.cglibProxy;

import org.apache.catalina.User;
import structural.proxy.dynamicProxy.UserController;

/**
 * @author kangqing
 * @since 2024/3/16 16:45
 */
public class CglibClient {
    public static void main(String[] args) {
        UserController userController = (UserController) CglibProxyFactory.createCglibProxy(UserController.class);
        userController.login();
        userController.register();
    }
}
