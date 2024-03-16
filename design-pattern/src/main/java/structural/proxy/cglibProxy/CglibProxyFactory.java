package structural.proxy.cglibProxy;

import org.springframework.cglib.proxy.Enhancer;
import structural.proxy.MetricsService;

/**
 * @author kangqing
 * @since 2024/3/16 16:41
 */
public class CglibProxyFactory {

    public static Object createCglibProxy(Class<?> clazz) {
        final Enhancer enhancer = new Enhancer();
        // 为加强器指定要代理的业务类
        enhancer.setSuperclass(clazz);
        MetricsService service = new MetricsService();
        // 设置回调，对于代理类上的所有方法的调用，都会回调CallBack, 而CallBack需要实现 intercept()方法
        enhancer.setCallback(new CglibProxy(service));
        return enhancer.create();
    }
}
