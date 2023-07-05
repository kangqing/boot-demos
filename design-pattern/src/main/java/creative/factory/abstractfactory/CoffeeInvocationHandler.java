package creative.factory.abstractfactory;

import cn.hutool.core.util.ClassLoaderUtil;
import creative.factory.first.Coffee;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author kangqing
 * @since 2023/7/5 15:37
 */
public class CoffeeInvocationHandler implements InvocationHandler {

    private Coffee coffee;

    public CoffeeInvocationHandler(Coffee coffee) {
        this.coffee = coffee;
    }

    /**
     *
     * @param proxy
     * @param method
     * @param args  参数，可以为空
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return Coffee.class
                .getMethod(method.getName(), ClassLoaderUtils.getClazzByArgs(args))
                .invoke(coffee, args);
    }

}
