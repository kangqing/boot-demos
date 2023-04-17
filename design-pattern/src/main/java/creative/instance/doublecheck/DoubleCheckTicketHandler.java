package creative.instance.doublecheck;

import creative.instance.TicketHandler;

import java.util.concurrent.TimeUnit;

/**
 * 双重检查模式，懒加载
 * @author kangqing
 * @since 2023/4/10 07:27
 */
public class DoubleCheckTicketHandler extends TicketHandler {

    private static DoubleCheckTicketHandler INSTANCE;

    private DoubleCheckTicketHandler() {}

    public static DoubleCheckTicketHandler getInstance() {
        if (INSTANCE == null) {
            synchronized (DoubleCheckTicketHandler.class) {
                try {
                    TimeUnit.MILLISECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (INSTANCE == null) {
                    INSTANCE = new DoubleCheckTicketHandler();
                }
            }
        }
        return INSTANCE;
    }

}
