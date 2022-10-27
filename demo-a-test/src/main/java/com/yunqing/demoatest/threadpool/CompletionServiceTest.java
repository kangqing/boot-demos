package com.yunqing.demoatest.threadpool;

import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 批量处理线程
 * @author kangqing
 * @since 2022/5/24 13:32
 */
public class CompletionServiceTest {
    @SneakyThrows
    public static void main(String[] args) {
        // 线程池
        final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(3, 5, 60L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>());
        // 实现批量的工具类
        CompletionService<Integer> service = new ExecutorCompletionService(threadPoolExecutor);
        // 结果会存入队列中，不指定的话默认 LinkedBlockingQueue
        service.submit(() -> {
            System.out.println("线程1");
            return 1;
        });
        service.submit(() -> {
            System.out.println("线程2");
            return 2;
        });
        // 异步执行阻塞队列中的结果
        for (int i = 0; i < 2; i++) {
            Integer a = service.take().get();
            threadPoolExecutor.execute(() -> save(a));
        }


    }

    private static void save(Integer a) {
    }

    /**
     * 使用 ExecutorCompletionService
     * 利用 CompletionService 可以快速实现 Forking 这种集群模式，比如下面的示例代码就展示了具体是如何实现的。
     * 首先我们创建了一个线程池 executor 、一个 CompletionService 对象 cs 和一个Future类型的列表 futures，
     * 每次通过调用 CompletionService 的 submit() 方法提交一个异步任务，会返回一个 Future 对象，我们把这些
     * Future 对象保存在列表 futures 中。通过调用 cs.take().get()，我们能够拿到最快返回的任务执行结果，只要
     * 我们拿到一个正确返回的结果，就可以取消所有任务并且返回最终结果了。
     * @return
     */
    @SneakyThrows
    public int method() {

        // 创建线程池
        ExecutorService executor = Executors.newFixedThreadPool(3);
        // 创建CompletionService
        CompletionService<Integer> cs = new ExecutorCompletionService<>(executor);
        // 用于保存Future对象
        List<Future<Integer>> futures = new ArrayList<>(3);
        //提交异步任务，并保存future到futures
        futures.add(
                cs.submit(this::geocoderByS1));
        futures.add(
                cs.submit(this::geocoderByS2));
        futures.add(
                cs.submit(this::geocoderByS3));
        // 获取最快返回的任务执行结果
        Integer r = 0;
        try {
            // 只要有一个成功返回，则break
            for (int i = 0; i < 3; ++i) {
                r = cs.take().get();
                //简单地通过判空来检查是否成功返回
                if (r != null) {
                    break;
                }
            }
        } finally {
            //取消所有任务
            for(Future<Integer> f : futures)
                f.cancel(true);
        }
        // 返回结果
        return r;
    }

    private Integer geocoderByS3() {
        return 0;
    }

    private Integer geocoderByS2() {
        return 0;
    }

    private Integer geocoderByS1() {
        return 0;
    }


}

