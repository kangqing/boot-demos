package proxy.basicExtends;

import proxy.MetricsService;
import proxy.RequestInfo;
import proxy.basicExtends.UserController;
import proxy.basicInterface.IUserController;

/**
 * 代理类需要继承被代理的类
 * @author kangqing
 * @since 2023/4/13 06:51
 */
public class UserControllerProxy extends UserController {
    private MetricsService service;

    // 构造器注入
    public UserControllerProxy() {
        this.service = new MetricsService();
    }

    public void login() {
        long start = System.currentTimeMillis();

        super.login();

        long end = System.currentTimeMillis();
        long response = end - start;
        RequestInfo info = new RequestInfo("login", response, start);
        service.recordRequest(info);
    }

    public void register() {
        long start = System.currentTimeMillis();

        super.register();

        long end = System.currentTimeMillis();
        long response = end - start;
        RequestInfo info = new RequestInfo("register", response, start);
        service.recordRequest(info);
    }
}
