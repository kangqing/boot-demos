package proxy.dynamicProxy;

import proxy.basicInterface.IUserController;

/**
 * @author kangqing
 * @since 2023/4/13 07:23
 */
public class DynamicClient {
    public static void main(String[] args) {
        final UserController userController = new UserController();
        MetricsDynamicProxy proxy = new MetricsDynamicProxy();
        IUserController user = (IUserController) proxy.createProxy(userController);
        user.login();
        user.register();
    }
}
