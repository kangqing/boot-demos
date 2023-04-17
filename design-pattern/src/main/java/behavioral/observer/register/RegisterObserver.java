package behavioral.observer.register;

/**
 * 针对用户注册行为的观察
 * 观察到用户注册成功后执行其他逻辑，发放优惠券、发送站内信等等
 * @author kangqing
 * @since 2023/4/18 07:26
 */
public interface RegisterObserver {
    // 用户注册成功
    void handleRegisterSuccess(Long userId);
}
