import lombok.SneakyThrows;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * <p> #{java.util.concurrent.Future}Future<T> 的使用方法</p>
 * @author kangqing
 * @since 2023/4/5 07:20
 */
public class FutureTest {
    @SneakyThrows
    public static void main(String[] args) {
        final FutureSolution futureSolution = new FutureSolution();
        final Future<Integer> cal = futureSolution.cal(10);
        while (!cal.isDone()) {
            System.out.println("计算中...");
            // 取消计算
            cal.cancel(true);
            TimeUnit.MILLISECONDS.sleep(300);
        }
        // 如果取消执行，结束线程池
        if (cal.isCancelled()) {
            futureSolution.shutdown();
            System.out.println("使用 cancel 取消了计算");
            return;
        }
        // 即使取消了，也会走到这一步才抛出CancellationException异常，可在此步之前判断，return
        Integer integer = cal.get();
        System.out.println("计算结果" + integer);

        futureSolution.shutdown();
    }
}

class FutureSolution {
    // 一个单线程处理的线程池
    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    /**
     * 计算一个数字的平方，延迟1秒返回结果
     * 因为Future接收的是一个将来的结果
     * @param number
     * @return
     */
    public Future<Integer> cal(Integer number) {
        System.out.println("begin cal >>> input = " + number);
        return executor.submit(() -> {
            TimeUnit.SECONDS.sleep(1);
            return number * number;
        });

    }

    public void shutdown() {
        executor.shutdown();
    }
}
