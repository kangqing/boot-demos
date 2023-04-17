package structural.proxy.basicInterface;

import structural.proxy.MetricsService;
import structural.proxy.RequestInfo;
import structural.proxy.dynamicProxy.UserController;

/**
 * 代理类需要继承被代理的类
 * @author kangqing
 * @since 2023/4/13 06:51
 */
public class UserControllerProxy implements IUserController {
    private MetricsService service;
    private UserController userController;

    // 构造器注入
    public UserControllerProxy(UserController userController) {
        this.service = new MetricsService();
        this.userController = userController;
    }

    @Override
    public void login() {
        long start = System.currentTimeMillis();

        userController.login();

        long end = System.currentTimeMillis();
        long response = end - start;
        RequestInfo info = new RequestInfo("login", response, start);
        service.recordRequest(info);
    }

    @Override
    public void register() {
        long start = System.currentTimeMillis();

        userController.register();

        long end = System.currentTimeMillis();
        long response = end - start;
        RequestInfo info = new RequestInfo("register", response, start);
        service.recordRequest(info);
    }
}
