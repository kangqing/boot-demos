package creative.factory.staticFactory;

/**
 * 咖啡店生产咖啡
 * @author kangqing
 * @since 2023/4/11 21:20
 */
public class CoffeeStore {

    private CoffeeFactory factory;

    public void setFactory(CoffeeFactory factory) {
        this.factory = factory;
    }

     public Coffee orderCoffee() {
         final Coffee coffee = factory.createCoffee();
         // 加糖加奶交给咖啡店去做
         coffee.addMilk();
         coffee.addSugar();
         return coffee;
     }
}
