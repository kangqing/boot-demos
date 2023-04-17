package structural.proxy.basicInterface;


import structural.proxy.dynamicProxy.UserController;

/**
 * @author kangqing
 * @since 2023/4/13 06:55
 */
public class BasicInterfaceClient {

    public static void main(String[] args) {
        final UserController userController = new UserController();

        IUserController proxy = new UserControllerProxy(userController);

        proxy.login();
        proxy.register();
    }
}
