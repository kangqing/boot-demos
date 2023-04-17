package creative.factory.simplefactory;

/**
 * 咖啡简单工厂
 * 简单工厂方法一：转移实现到一个新的工厂类中Factory结尾，方法以create开始，一般静态方法
 * @author kangqing
 * @since 2023/4/11 21:30
 */
public class SimpleCoffeeFactory {

    /**
     * 创建咖啡
     * @param type
     * @return
     */
    public static Coffee createCoffee(String type) {
        Coffee coffee = null;
        if ("American".equals(type)) {
            coffee = new AmericanCoffee();
        } else if ("Latte".equals(type)) {
            coffee = new LatteCoffee();
        } else {
            throw new IllegalArgumentException("你点的咖啡不存在");
        }
        return coffee;
    }
}
