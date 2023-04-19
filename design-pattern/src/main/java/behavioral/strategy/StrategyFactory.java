package behavioral.strategy;

import com.google.common.base.Preconditions;

import java.util.HashMap;
import java.util.Map;

/**
 * 策略工厂
 * @author kangqing
 * @since 2023/4/19 21:52
 */
public class StrategyFactory {

    private final static Map<String, PayStrategy> map = new HashMap<>();
    static {
        map.put("1", new PayByAlipay());
        map.put("2", new PayByWeChat());
    }

    public static PayStrategy getStrategy(String type) {
        Preconditions.checkNotNull(type);
        return map.get(type);
    }
}

/**
 * 有时候策略模式并不是移除了if-else而是，转移到了工厂方法中
 */
class StrategyFactory2 {
    
    public static PayStrategy getStrategy(String type) {
        if ("1".equals(type)) {
            return new PayByAlipay();
        } else if ("2".equals(type)) {
            return new PayByWeChat();
        } else {
            throw new IllegalArgumentException("无此支付方式");
        }
    }
    
}
