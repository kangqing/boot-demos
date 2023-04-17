package creative.instance.enummodel;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author kangqing
 * @since 2023/4/10 07:36
 */
public enum EnumTicketHandler {
    INSTANCE;

    private AtomicLong nextTicket = new AtomicLong(1);

    public Long getNextTicket() {
        return nextTicket.getAndIncrement();
    }
}
