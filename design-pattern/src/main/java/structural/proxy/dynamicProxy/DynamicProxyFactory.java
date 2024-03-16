package structural.proxy.dynamicProxy;

import structural.proxy.MetricsService;
import structural.proxy.RequestInfo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 实现动态代理类
 * @author kangqing
 * @since 2023/4/13 07:11
 */
public class DynamicProxyFactory<T> {

     /**
      * 组合，记录耗时的打印日志类
      */
     private final MetricsService service;

     public DynamicProxyFactory() {
         this.service = new MetricsService();
     }

     // 创建代理，传入被代理的对象
     @SuppressWarnings(value = "unchecked")
     public T createProxy(T obj) {
          final Class<?>[] interfaces = obj.getClass().getInterfaces();
          DynamicProxyHandler<T> handler = new DynamicProxyHandler<>(obj);
          return (T) Proxy.newProxyInstance(obj.getClass().getClassLoader(), interfaces, handler);
     }

     private class DynamicProxyHandler<U> implements InvocationHandler {

          private final U obj;

          public DynamicProxyHandler(U obj) {
               this.obj = obj;
          }

          @Override
          public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
               long start = System.currentTimeMillis();

               Object result = method.invoke(obj, args);

               long end = System.currentTimeMillis();
               long responseTime = end - start;

               String apiName = obj.getClass().getName() + ":" + method.getName();

               RequestInfo info = new RequestInfo(apiName, responseTime, start);
               service.recordRequest(info);
               return result;
          }
     }
}
