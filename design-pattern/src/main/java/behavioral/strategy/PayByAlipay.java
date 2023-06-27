package behavioral.strategy;

/**
 * 支付宝支付策略
 * @author kangqing
 * @since 2023/4/19 21:48
 */
public class PayByAlipay implements PayStrategy{
    @Override
    public void pay(int money) {
        if (money > 100) {
            money-=10;
            System.out.println("使用支付宝支付，满100优惠10元，共支付 = " + money);
            return;
        }
        System.out.println("使用支付宝支付 = " + money);
    }
}
