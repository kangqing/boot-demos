package behavioral.observer.register;

/**
 * 实现观察注册是否成功，成功则发送优惠券
 * @author kangqing
 * @since 2023/4/18 07:30
 */
public class ObserverImplOne implements RegisterObserver{
    @Override
    public void handleRegisterSuccess(Long userId) {
        System.out.println("给用户发送优惠券成功，用户 = " + userId);
    }
}
