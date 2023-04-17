package structural.decorator;

/**
 * 装饰器类，增强装饰，加入其他元素的装饰
 * @author kangqing
 * @since 2023/4/13 19:56
 */
public class OtherDecorator extends BaseDecorator{
    public OtherDecorator(IDecorator iDecorator) {
        super(iDecorator);
    }

    @Override
    public void decorate() {
        super.decorate();
        // 添加的功能
        System.out.println("贴地板装");
    }
}
