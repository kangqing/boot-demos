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
public class MetricsDynamicProxy {

     private MetricsService service;

     public MetricsDynamicProxy() {
         this.service = new MetricsService();
     }

     // 创建代理，传入被代理的对象
     public Object createProxy(Object proxiedObject) {
          final Class<?>[] interfaces = proxiedObject.getClass().getInterfaces();
          DynamicProxyHandler handler = new DynamicProxyHandler(proxiedObject);
          return Proxy.newProxyInstance(proxiedObject.getClass().getClassLoader(), interfaces, handler);
     }

     private class DynamicProxyHandler implements InvocationHandler {

          private Object proxiedObject;

          public DynamicProxyHandler(Object proxiedObject) {
               this.proxiedObject = proxiedObject;
          }

          @Override
          public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
               long start = System.currentTimeMillis();

               Object result = method.invoke(proxiedObject, args);

               long end = System.currentTimeMillis();
               long responseTime = end - start;

               String apiName = proxiedObject.getClass().getName() + ":" + method.getName();

               RequestInfo info = new RequestInfo(apiName, responseTime, start);
               service.recordRequest(info);
               return result;
          }
     }
}
