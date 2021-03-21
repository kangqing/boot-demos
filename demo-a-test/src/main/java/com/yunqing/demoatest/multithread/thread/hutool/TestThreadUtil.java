package com.yunqing.demoatest.multithread.thread.hutool;


import cn.hutool.core.thread.GlobalThreadPool;
import cn.hutool.core.thread.ThreadUtil;
import lombok.SneakyThrows;

import java.util.List;
import java.util.concurrent.*;

/**
 * 注：IO密集型（某大厂实践经验）
 *        核心线程数 = CPU核数 / （1-阻塞系数）
 * 或着
 * CPU密集型：核心线程数 = CPU核数 + 1
 * IO密集型：核心线程数 = CPU核数 * 2
 * @author kangqing
 * @since 2021/3/17 17:50
 */
public class TestThreadUtil {
    //获取当前机器的核数
    private static final int cpuNum = Runtime.getRuntime().availableProcessors();

    @SneakyThrows
    public static void main(String[] args) {
        Runnable r1 = () -> {
            System.out.println("000000");
        };

        Callable<Object> callable = () -> "11111";
        Callable<Object> callable1 = () -> 11;

        // 新建线程池
        ThreadPoolExecutor threadPoolExecutor = ThreadUtil.newExecutor(cpuNum, cpuNum * 2);
        Future<?> submit = threadPoolExecutor.submit(callable);
        System.out.println(submit.get());
        threadPoolExecutor.execute(r1);
        System.out.println(threadPoolExecutor.getCorePoolSize() + "---" + threadPoolExecutor.getMaximumPoolSize());
        // 线程池用完关闭
        threadPoolExecutor.shutdown();

        // 使用 GlobalThreadPool 直接在公共线程池中执行线程 队列是 SynchronousQueue()
        ThreadUtil.execute(r1);
        // 使用 公共线程 异步提交 submit
        ThreadUtil.execAsync(r1);
        // 创建CompletionService，调用其submit方法可以异步执行多个任务，
        // 最后调用take方法按照完成的顺序获得其结果。若未完成，则会阻塞。
        CompletionService<Object> objectCompletionService = ThreadUtil.newCompletionService();
        objectCompletionService.submit(callable);
        objectCompletionService.submit(callable1);
        for (int i = 0; i < 2; i++) {
            // 取出线程执行返回结果
            Future<Object> take = objectCompletionService.take();
            System.out.println("批量=====" + take.get());
        }




    }

}
