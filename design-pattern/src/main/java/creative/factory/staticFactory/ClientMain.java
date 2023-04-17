package creative.factory.staticFactory;

/**
 * 无设计模式的方式
 * @author kangqing
 * @since 2023/4/11 21:24
 */
public class ClientMain {
    public static void main(String[] args) {
        final CoffeeStore coffeeStore = new CoffeeStore();
        final CoffeeFactory factory = new AmericanCoffeeFactory();
        coffeeStore.setFactory(factory);
        // 点咖啡
        final Coffee coffee = coffeeStore.orderCoffee();
        System.out.println(coffee.getName());
    }
}
