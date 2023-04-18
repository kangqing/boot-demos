package behavioral.observer.eventbus;

import behavioral.observer.register.RegisterObserver;
import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * 基于 Google Guava EventBus 这个轮子实现观察者模式，无需抽象出一个公用的接口，任意类型对象都可以注册到 EventBus 中
 *
 * @author kangqing
 * @since 2023/4/18 07:32
 */
public class UserController {

     private EventBus eventBus;

     public UserController() {
         //eventBus = new EventBus(); // 同步阻塞观察者模式

         // 异步非阻塞观察者模式
         eventBus = new AsyncEventBus(Executors.newFixedThreadPool(20));
     }

     // 一次设置好观察者，之后不可能动态修改
     public void setRegisterObservers(List<Object> observers) {
         for (Object object : observers) {
             eventBus.register(object);
         }
     }

     public Long register() {
         // 省略注册的代码,模拟注册成功，用户id是1001
         long userId = 1001;
         // 注册成功处理逻辑, 直接 post 方法向观察者发送消息
         eventBus.post(userId);
         return userId;
     }
}
