package creative.instance.lazy;

import creative.instance.TicketHandler;

import java.util.concurrent.TimeUnit;

/**
 * 懒汉单例，会有线程安全问题
 * @author kangqing
 * @since 2023/4/9 16:05
 */
public class LazyTicketHandler extends TicketHandler {

    // 保存单例的实例对象
    private static LazyTicketHandler INSTANCE;
    // 私有化构造器，防止new
    private LazyTicketHandler() {}

    // 懒加载
    public static LazyTicketHandler getInstance() {
        if (INSTANCE == null) {
            try {
                // 模拟单例模式执行，因为无论什么业务逻辑，都不会立刻结束
                TimeUnit.MILLISECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            INSTANCE = new LazyTicketHandler();
        }
        return INSTANCE;
    }


}
