package proxy.dynamicProxy;


import proxy.basicInterface.IUserController;

import java.util.concurrent.TimeUnit;

/**
 * 静态代理的缺点：
 *      ①. 需要在代理类中重写所有方法
 *      ②. 每一个代理需要创建一个静态代理类
 * 动态代理的优势：
 *      使用之前不需要创建代理类，在使用的时候，通过反射，动态的创建代理类，并动态替换原始类执行。
 *
 * @author kangqing
 * @since 2023/4/13 06:30
 */
public class UserController implements IUserController {

    @Override
    public void login() {
        // 模拟登录逻辑
        try {
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void register() {
        // 模拟注册逻辑
        try {
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
