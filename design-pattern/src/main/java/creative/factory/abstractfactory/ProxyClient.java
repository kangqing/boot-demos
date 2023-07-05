package creative.factory.abstractfactory;

import creative.factory.first.AmericanCoffee;
import creative.factory.first.Coffee;
import creative.factory.first.LatteCoffee;

/**
 * @author kangqing
 * @since 2023/7/5 16:03
 */
public class ProxyClient {
    public static void main(String[] args) {
        final Coffee latteCoffee = JDKProxy.getProxy(LatteCoffee.class, new LatteCoffee());
        final String name = latteCoffee.getName();
        System.out.println(name);
        latteCoffee.addMilk();

        final Coffee americanCoffee = JDKProxy.getProxy(AmericanCoffee.class, new AmericanCoffee());
        System.out.println(americanCoffee.getName());
        americanCoffee.addSugar();
    }
}
