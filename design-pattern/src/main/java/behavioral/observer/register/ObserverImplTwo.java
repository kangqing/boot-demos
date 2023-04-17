package behavioral.observer.register;

/**
 * 给用户发送站内信
 * @author kangqing
 * @since 2023/4/18 07:31
 */
public class ObserverImplTwo implements RegisterObserver{
    @Override
    public void handleRegisterSuccess(Long userId) {
        System.out.println("给用户发送站内信成功，用户 = " + userId);
    }
}
