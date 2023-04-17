package creative.factory.staticFactory;

/**
 * 美式咖啡工厂对象，专门生产美式咖啡
 * @author kangqing
 * @since 2023/4/11 21:50
 */
public class AmericanCoffeeFactory implements CoffeeFactory{
    @Override
    public Coffee createCoffee() {
        return new AmericanCoffee();
    }
}
