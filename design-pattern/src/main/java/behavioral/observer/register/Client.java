package behavioral.observer.register;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author kangqing
 * @since 2023/4/18 20:46
 */
public class Client {

    public static void main(String[] args) {
        final ObserverImplOne observerImplOne = new ObserverImplOne();
        final ObserverImplTwo observerImplTwo = new ObserverImplTwo();
        List<RegisterObserver> list = new ArrayList<>();
        list.add(observerImplOne);
        list.add(observerImplTwo);
        final ExecutorService executorService = Executors.newFixedThreadPool(20);
        final UserController userController = new UserController(executorService);
        userController.setRegisterObservers(list);
        userController.register();
    }
}
