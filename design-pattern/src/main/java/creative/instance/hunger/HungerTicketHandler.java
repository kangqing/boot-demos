package creative.instance.hunger;

import creative.instance.TicketHandler;

/**
 * 饿汉模式,线程安全
 * @author kangqing
 * @since 2023/4/10 06:58
 */
public class HungerTicketHandler extends TicketHandler {

    private static HungerTicketHandler INSTANCE = new HungerTicketHandler();

    private HungerTicketHandler() {}

    public static HungerTicketHandler getInstance() {
        return INSTANCE;
    }
}
