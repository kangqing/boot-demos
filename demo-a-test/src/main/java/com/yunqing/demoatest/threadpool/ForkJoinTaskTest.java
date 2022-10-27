package com.yunqing.demoatest.threadpool;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * java的分治计算框架
 * @author kangqing
 * @since 2022/6/3 15:09
 */
public class ForkJoinTaskTest {
    public static void main(String[] args) {
        // 创建分治任务线程池
        ForkJoinPool forkJoin = new ForkJoinPool(4);
        // 创建分治任务
        Fibonacci fibonacci = new Fibonacci(30);
        // 启动分治任务
        Integer result = forkJoin.invoke(fibonacci);
        // 输出结果
        System.out.println("斐波那契数量的结果 = " + result);

        // 验证单词数量
        String[] fc = {"hello world",
                "hello me",
                "hello fork",
                "hello join",
                "fork join in world"};
        // 创建线程池
        ForkJoinPool fjp = new ForkJoinPool(3);
        // 创建任务
        MR mr = new MR(fc, 0, fc.length);
        // 启动任务
        final Map<String, Long> invoke = fjp.invoke(mr);
        // 输出结果
        invoke.forEach((k, v) -> System.out.println("单词" + k + "的数量 = " + v));

    }
}

/**
 * 计算斐波那契数列的子任务
 */
class Fibonacci extends RecursiveTask<Integer> {

    final int n;

    Fibonacci(int n) {
        this.n = n;
    }

    @Override
    protected Integer compute() {
        if (n <= 1) {
            return n;
        }
        Fibonacci f1 = new Fibonacci(n - 1);
        // 创建子任务
        f1.fork();
        Fibonacci f2 = new Fibonacci(n - 2);
        // 等待子任务结果，并合并结果
        return f2.compute() + f1.join();
    }
}

/**
 * 处理文件中有多少个单词
 */
class MR extends RecursiveTask<Map<String, Long>> {

    private String[] fc;
    private int start, end;

    MR(String[] fc, int from, int to) {
        this.fc = fc;
        this.start = from;
        this.end = to;
    }

    @Override
    protected Map<String, Long> compute() {
        if (end - start == 1) {
            return calc(fc[start]);
        } else {
            // 二分法
            int mid = (start + end) / 2;
            MR mr1 = new MR(fc, start, mid);
            // 分治，创建子任务
            mr1.fork();
            MR mr2 = new MR(fc, mid, end);
            // 计算子任务，并返回合并的结果
            return merge(mr2.compute(), mr1.join());
        }
    }

    // 合并结果
    private Map<String, Long> merge(Map<String, Long> r1, Map<String, Long> r2) {
        Map<String, Long> result = new HashMap<>(r1);
        // 合并结果
        r2.forEach((k, v) -> result.merge(k, v, Long::sum));
        return result;
    }

    /**
     * 统计当前行单词数量
     * @param line
     * @return
     */
    private Map<String, Long> calc(String line) {
        Map<String, Long> map = new HashMap<>();
        // 分割单词
        String[] words = line.split("\\s+");
        // 统计单词数量
        for (String word: words) {
            Long val = map.get(word);
            if (val != null) {
                map.put(word, val + 1);
            } else {
                map.put(word, 1L);
            }
        }
        return map;
    }
}
