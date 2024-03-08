package creative.factory.abstractfactory01;

import creative.factory.abstractfactory.ClassLoaderUtils;
import creative.factory.abstractfactory01.adapter.ICacheAdapter;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author kangqing
 * @since 2024/3/7 22:42
 */
public class JDKInvocationHandler implements InvocationHandler {
    private final ICacheAdapter cacheAdapter;
    public JDKInvocationHandler(ICacheAdapter cacheAdapter) {
        this.cacheAdapter = cacheAdapter;
    }
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
        return ICacheAdapter.class.getMethod(method.getName(),
                ClassLoaderUtils.getClazzByArgs(args)).invoke(cacheAdapter, args);
    }
}