package decorator;

/**
 * 客户端，调用其他装饰器类
 * @author kangqing
 * @since 2023/4/13 19:59
 */
public class Client {
    public static void main(String[] args) {
        IDecorator iDecorator = new Decorator();
        final OtherDecorator otherDecorator = new OtherDecorator(iDecorator);
        otherDecorator.decorate();
    }
}
