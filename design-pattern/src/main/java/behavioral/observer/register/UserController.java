package behavioral.observer.register;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

/**
 * 需要有一个存储哪些订阅者的地方
 * @author kangqing
 * @since 2023/4/18 07:32
 */
public class UserController {

     private Executor executor;

     private List<RegisterObserver> registerObservers = new ArrayList<>();

     public UserController(Executor executor) {
         this.executor = executor;
     }
     // 一次设置好观察者，之后不可能动态修改
     public void setRegisterObservers(List<RegisterObserver> observers) {
         registerObservers.addAll(observers);
     }

     public Long register() {
         // 省略注册的代码,模拟注册成功，用户id是1001
         long userId = 1001;
         // 注册成功处理逻辑
         for (RegisterObserver observer: registerObservers) {
             // 异步处理，更好的办法是把观察者异步模式抽象成框架 EventBus
             executor.execute(() -> {
                 observer.handleRegisterSuccess(userId);
             });

         }
         return userId;
     }
}
