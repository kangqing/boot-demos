package creative.instance.lazy;

import creative.instance.TicketHandler;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 懒汉单例，多线程情况下会重复出票
 * @author kangqing
 * @since 2023/4/9 16:10
 */
public class LazyTicketHandlerTest {
    public static void main(String[] args) {
        // 用户人数
        int userNum = 10000;

        // 线程集合
        Set<Thread> threadSet = new HashSet<>();
        // 存放实例对象
        List<TicketHandler> ticketHandlers = new CopyOnWriteArrayList<>(new ArrayList<>(userNum));
        // 保存生产的车票
        List<Long> ticketList = new CopyOnWriteArrayList<>(new ArrayList<>(userNum));
        // 定义购票线程，一个线程模拟一个用户
        for (int i = 0; i < userNum; i++) {
            Thread t = new Thread(() -> {
                // 生产懒加载单例
                TicketHandler handler = LazyTicketHandler.getInstance();
                // 增加实例对象到list
                ticketHandlers.add(handler);
                // 生产车票号
                final Long nextTicket = handler.getNextTicket();
                ticketList.add(nextTicket);
            });
            threadSet.add(t);
        }

        System.out.println("当前购票人数 = " + threadSet.size());
        // 上面的add还没有结束，这里如果使用 foreach循环则会出现 ConcurrentModificationException
        // java8 的 Foreach（e -> e.start()）也会有出现此异常的机会
        // 迭代器也不行，也会出现异常,因为ArrayList甚至Vector也不行，在多线程情况下，一遍添加，一边遍历会失败
        // 这里使用并发容器 CopyOnWriteArrayList 解决
        for (Thread thread : threadSet) {
            thread.start();
        }

        System.out.println("车票生产类实例对象数量 = " + new HashSet(ticketHandlers).size());

        System.out.println("共出票数量 = " + ticketList.size());

        System.out.println("实际出票数量 = " + new HashSet(ticketList).size());
    }
}
