package behavioral.observer.eventbus;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kangqing
 * @since 2023/4/18 20:43
 */
public class EventBusClient {
    public static void main(String[] args) {
        List<Object> list = new ArrayList<>();
        list.add(new ObserverImplOne());
        list.add(new ObserverImplTwo());

        final UserController userController = new UserController();
        userController.setRegisterObservers(list);
        userController.register();
    }
}
