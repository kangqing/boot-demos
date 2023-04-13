package decorator;

/**
 * 实现基本装修项目
 * @author kangqing
 * @since 2023/4/13 19:51
 */
public class Decorator implements IDecorator{
    @Override
    public void decorate() {
        System.out.println("水电接入");
    }
}
