package structural.proxy.dynamicProxy;

import structural.proxy.basicInterface.IUserController;

/**
 * @author kangqing
 * @since 2023/4/13 07:23
 */
public class DynamicClient {
    public static void main(String[] args) {
        final UserController userController = new UserController();
        DynamicProxyFactory<IUserController> proxy = new DynamicProxyFactory<>();
        IUserController user = proxy.createProxy(userController);
        user.login();
        user.register();
    }
}
