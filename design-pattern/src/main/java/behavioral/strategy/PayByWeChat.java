package behavioral.strategy;

/**
 * 微信支付策略
 * @author kangqing
 * @since 2023/4/19 21:50
 */
public class PayByWeChat implements PayStrategy{
    @Override
    public void pay(int money) {
        System.out.println("使用微信支付 = " + money);
    }
}
