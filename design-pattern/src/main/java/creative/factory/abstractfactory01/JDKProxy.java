package creative.factory.abstractfactory01;

import creative.factory.abstractfactory01.adapter.ICacheAdapter;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @author kangqing
 * @since 2024/3/7 22:39
 */
public class JDKProxy {
    @SuppressWarnings(value = "unchecked")
    public static <T> T getProxy(Class<T> interfaceClass, ICacheAdapter
            cacheAdapter) throws Exception {
        InvocationHandler handler = new JDKInvocationHandler(cacheAdapter);
        ClassLoader classLoader =
                Thread.currentThread().getContextClassLoader();
        Class<?>[] classes = interfaceClass.getInterfaces();
        return (T) Proxy.newProxyInstance(classLoader, new Class[]{classes[0]},
                handler);
    }
}
