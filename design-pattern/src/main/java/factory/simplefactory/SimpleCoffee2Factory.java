package factory.simplefactory;

import java.util.HashMap;
import java.util.Map;

/**
 * 简单工厂缓存模式
 * 第二种简单工厂实现
 * @author kangqing
 * @since 2023/4/11 21:36
 */
public class SimpleCoffee2Factory {

    private static final Map<String, Coffee> map = new HashMap<>();
    static {
        map.put("American", new AmericanCoffee());
        map.put("Latte", new LatteCoffee());
    }

    public static Coffee createCoffee(String type) {
        final Coffee coffee = map.get(type);
        if (coffee == null) {
            throw new IllegalArgumentException("你点的咖啡不存在");
        }
        return coffee;
    }
}
