package creative.factory.abstractfactory;

import creative.factory.first.Coffee;

import java.lang.reflect.Proxy;

/**
 * @author kangqing
 * @since 2023/7/5 15:34
 */
public class JDKProxy {

    /**
     *
     * @param interfaceClass 接口实现类，为了找到此类的实现的接口
     * @param coffee
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T getProxy(Class<T> interfaceClass, Coffee coffee) {
        final CoffeeInvocationHandler handler = new CoffeeInvocationHandler(coffee);
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Class<?>[] clazz = interfaceClass.getInterfaces();
        return (T) Proxy.newProxyInstance(classLoader, new Class[]{clazz[0]}, handler);
    }
}
