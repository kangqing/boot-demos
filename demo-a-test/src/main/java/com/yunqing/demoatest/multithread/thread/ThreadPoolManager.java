package com.yunqing.demoatest.multithread.thread;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.*;

/**
 * 线程池统一调度管理
 * @author kangqing
 * @since 2021/3/17 16:43
 */
public final class ThreadPoolManager {

    private static ThreadPoolManager sThreadPoolManager = null;

    // 核心线程数量
    private static final int SIZE_CORE_POOL = 3;

    // 线程池维护线程的最大数量
    private static final int SIZE_MAX_POOL = 10;

    // 线程池维护线程所允许的空闲时间
    private static final int TIME_KEEP_ALIVE = 5000;

    // 线程池所使用的缓冲队列大小
    private static final int SIZE_WORK_QUEUE = 500;

    // 任务调度周期
    private static final int PERIOD_TASK_QOS = 1000;

    /*
     * 线程池单例创建方法
     */
    public static ThreadPoolManager getThreadPoolManager() {
        if (sThreadPoolManager == null) {
            synchronized (ThreadPoolManager.class) {
                if (sThreadPoolManager == null) {
                    sThreadPoolManager = new ThreadPoolManager();
                }
            }
        }
        return sThreadPoolManager;
    }

    // 任务缓冲队列
    private final Queue<Runnable> mTaskQueue = new LinkedList<>();

    /*
     * 线程池数量达到核心线程数时将加入阻塞队列
     */
    private final RejectedExecutionHandler mHandler = (task, executor) -> mTaskQueue.offer(task);

    /*
     * 将缓冲队列中的任务重新加载到线程池
     */
    private final Runnable mAccessBufferThread = new Runnable() {
        @Override
        public void run() {
            if (hasMoreAcquire()) {
                mThreadPool.execute(mTaskQueue.poll());
            }
        }
    };

    /*
     * 创建一个调度线程池
     */
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    /*
     * 通过调度线程周期性的执行缓冲队列中任务
     */
    protected final ScheduledFuture<?> mTaskHandler = scheduler.scheduleAtFixedRate(mAccessBufferThread, 0,
            PERIOD_TASK_QOS, TimeUnit.MILLISECONDS);

    /*
     * 线程池
     */
    private final ThreadPoolExecutor mThreadPool = new ThreadPoolExecutor(SIZE_CORE_POOL, SIZE_MAX_POOL,
            TIME_KEEP_ALIVE, TimeUnit.SECONDS, new ArrayBlockingQueue<>(SIZE_WORK_QUEUE), mHandler);

    /*
     * 将构造方法访问修饰符设为私有，禁止任意实例化。
     */
    private ThreadPoolManager() {
    }

    //启动所有核心线程，使它们闲置地等待工作。这将覆盖仅在执行新任务时启动核心线程的默认策略。
    // @return启动的线程数
    public void perpare() {
        if (mThreadPool.isShutdown() && !mThreadPool.prestartCoreThread()) {
            @SuppressWarnings("unused")
            int startThread = mThreadPool.prestartAllCoreThreads();
        }
    }

    /*
     * 消息队列检查方法
     */
    private boolean hasMoreAcquire() {
        return !mTaskQueue.isEmpty();
    }

    /*
     * 向线程池中添加任务方法
     */
    public void addExecuteTask(Runnable task) {
        if (task != null) {
            mThreadPool.execute(task);
        }
    }
    // 通过返回线程数量是否为 0 判断任务是否结束
    protected boolean isTaskEnd() {
        return mThreadPool.getActiveCount() == 0;
    }

    // 清空队列，停止线程池
    public void shutdown() {
        mTaskQueue.clear();
        mThreadPool.shutdown();
    }
}
