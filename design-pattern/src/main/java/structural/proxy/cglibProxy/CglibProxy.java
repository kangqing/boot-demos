package structural.proxy.cglibProxy;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import structural.proxy.MetricsService;
import structural.proxy.RequestInfo;

import java.lang.reflect.Method;

/**
 * cglib代理类
 * @author kangqing
 * @since 2024/3/16 16:35
 */
public class CglibProxy implements MethodInterceptor {

    /**
     * 组合，记录耗时的打印日志类
     */
    private final MetricsService service;

    public CglibProxy(MetricsService service) {
        this.service = service;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        long start = System.currentTimeMillis();

        Object result = methodProxy.invokeSuper(o, objects);

        long end = System.currentTimeMillis();
        long responseTime = end - start;

        String apiName = methodProxy.getClass().getName() + ":" + method.getName();

        RequestInfo info = new RequestInfo(apiName, responseTime, start);
        service.recordRequest(info);
        return result;
    }
}
