package proxy.basicExtends;

import java.util.concurrent.TimeUnit;

/**
 * 静态代理二：基于继承实现的静态代理
 * 加入被代理的类是一个三方类，不能创建接口让其实现，只能通过此法实现静态代理
 * @author kangqing
 * @since 2023/4/13 06:30
 */
public class UserController {

    public void login() {
        // 模拟登录逻辑
        try {
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void register() {
        // 模拟注册逻辑
        try {
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
