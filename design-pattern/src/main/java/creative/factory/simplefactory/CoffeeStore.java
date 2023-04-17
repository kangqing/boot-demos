package creative.factory.simplefactory;

/**
 * 咖啡店生产咖啡
 * @author kangqing
 * @since 2023/4/11 21:20
 */
public class CoffeeStore {

     public Coffee orderCoffee(String type) {
         // 工厂只负责生产咖啡
         //final Coffee coffee = SimpleCoffeeFactory.createCoffee(type);
         final Coffee coffee = SimpleCoffee2Factory.createCoffee(type);
         // 加糖加奶交给咖啡店去做
         coffee.addMilk();
         coffee.addSugar();
         return coffee;
     }
}
