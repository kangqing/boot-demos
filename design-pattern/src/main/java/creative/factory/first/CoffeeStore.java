package creative.factory.first;

/**
 * 咖啡店生产咖啡
 * @author kangqing
 * @since 2023/4/11 21:20
 */
public class CoffeeStore {

     public Coffee orderCoffee(String type) {
         Coffee coffee = null;
         if ("American".equals(type)) {
             coffee = new AmericanCoffee();
         } else if ("Latte".equals(type)) {
             coffee = new LatteCoffee();
         } else {
             throw new IllegalArgumentException("你点的咖啡不存在");
         }

         coffee.addMilk();
         coffee.addSugar();
         return coffee;
     }
}
