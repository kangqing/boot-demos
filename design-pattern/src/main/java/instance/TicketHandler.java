package instance;

import java.util.concurrent.atomic.AtomicLong;

/**
 * nextTicket++ 不具备原子性，包含三个操作，获取nextTicket的值，给获取到的值加1，将结果赋值给 nextTicket
 * @author kangqing
 * @since 2023/4/9 16:00
 */
public class TicketHandler {

    // 车票
    //private long nextTicket = 1;
    private AtomicLong nextTicket = new AtomicLong(1);

    // 获取车票
//    public Long getNextTicket() {
//        return nextTicket++;
//    }
    public long getNextTicket() {
        return nextTicket.getAndIncrement();
    }
}
