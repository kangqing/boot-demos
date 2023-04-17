package structural.decorator;

/**
 * @author kangqing
 * @since 2023/4/13 19:53
 */
public abstract class BaseDecorator implements IDecorator{

    private IDecorator iDecorator;

    public BaseDecorator(IDecorator iDecorator) {
        this.iDecorator = iDecorator;
    }

    /**
     * 调用基本装饰方法
     */
    @Override
    public void decorate() {
        if(iDecorator != null) {
            // 水电接入等
            iDecorator.decorate();
        }
    }
}
