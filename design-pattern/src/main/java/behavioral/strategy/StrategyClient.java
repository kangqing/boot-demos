package behavioral.strategy;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 策略模式客户端
 * @author kangqing
 * @since 2023/4/19 21:55
 */
public class StrategyClient {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        PayStrategy payStrategy = null;
        System.out.print("输入支付金额: ");
        int money = Integer.parseInt(reader.readLine());

        System.out.println("\nChoose pay type.\n" +
                "1 - Alipay\n" +
                "2 - WeChat");
        int type = Integer.parseInt(reader.readLine());

        payStrategy = StrategyFactory.getStrategy(String.valueOf(type));
        payStrategy.pay(money);
    }
}
