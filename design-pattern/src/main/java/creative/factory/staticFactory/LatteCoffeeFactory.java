package creative.factory.staticFactory;

/**
 * 拿铁咖啡工厂，专门生产拿铁咖啡
 * @author kangqing
 * @since 2023/4/11 21:52
 */
public class LatteCoffeeFactory implements CoffeeFactory{
    @Override
    public Coffee createCoffee() {
        return new LatteCoffee();
    }
}
