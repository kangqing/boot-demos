package creative.factory.simplefactory;

/**
 * @author kangqing
 * @since 2023/4/11 21:17
 */
public interface Coffee {

    String getName();

    default void addSugar() {
        System.out.println("加糖");
    }

    default void addMilk() {
        System.out.println("加奶");
    }
}
