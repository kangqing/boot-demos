## Future<T> 期货类
简单地说，Future类表示异步计算的未来结果。这个结果最终会在处理完成后出现在Future中。

长时间运行的方法非常适合异步处理和Future接口，因为我们可以在等待封装在 Future 中的任务完成时执行其他进程。

### 创建一个示例
对于我们的示例，我们将创建一个非常简单的类来计算Integer的平方。这绝对不属于长时间运行的方法类别，但我们将对其进行Thread.sleep()调用，
使其在完成前持续 1 秒：

```java
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
```
1. 实际执行计算的代码位包含在call()方法中，并作为 lambda 表达式提供。
2. Callable是表示返回结果的任务的接口，并且具有单个call()方法。在这里，我们使用 lambda 表达式创建了它的一个实例。
3. 创建Callable的实例不会带我们去任何地方；我们仍然必须将这个实例传递给执行程序，执行程序将负责在新线程中启动任务，并将有价值的Future对象
还给我们。这就是ExecutorService 的用武之地。
4. 有几种方法可以访问ExecutorService实例，其中大部分是由实用程序类Executors的静态工厂方法提供的。在此示例中，我们使用了基本的
newSingleThreadExecutor()，它为我们提供了一个能够一次处理单个线程的ExecutorService 。
5. 一旦我们有了ExecutorService对象，我们只需要调用submit()，将我们的Callable作为参数传递。然后submit()将启动任务并返回一个FutureTask 
对象，它是Future接口的实现。

### 消费 Future 期货
```java
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
```
1. 现在我们需要调用cal()，并使用返回的Future来获取结果Integer。Future API中的两种方法将帮助我们完成这项任务。
2. Future.isDone()告诉我们执行者是否完成了任务处理。如果任务完成，则返回true； 否则，它返回false。
3. 从计算中返回实际结果的方法是Future.get()。我们可以看到这个方法会阻塞执行直到任务完成。但是，这在我们的示例中不是问题，因为我们将通过调用
isDone()检查任务是否完成。
4. 方法get()将阻塞执行，直到任务完成。同样，这不会成为问题，因为在我们的示例中，只有在确保任务完成后才会调用get() 。所以在这种情况下，
future.get()总是会立即返回。
5. 值得一提的是get()有一个重载版本，它以超时和TimeUnit作为参数：
   Integer result = future.get(500, TimeUnit.MILLISECONDS);
   get(long, TimeUnit)和get()之间的区别在于，如果任务在指定的超时时间之前没有返回， 前者将抛出 TimeoutException 。

### 使用 cancel 取消执行
假设我们触发了一个任务，但由于某种原因，我们不再关心结果了。我们可以使用Future.cancel(boolean)告诉执行者停止操作并中断其底层线程：
1. 事实上，如果我们尝试从该实例调用get()，在调用cancel()之后，结果将是CancellationException。
2. 注意上面这句话，是调用cancel() 之后，直到调用 get() 才会抛出异常 CancellationException
3. Future.isCancelled()会告诉我们Future是否已经被取消。这对于避免获得CancellationException非常有用。所以在调用 get()之前，我们
就判断了Future.isCancelled()，如果取消了，则直接return

## ForkJoinTask 概述
ForkJoinTask是一个实现Future 的抽象类，能够在ForkJoinPool中运行由少量实际线程托管的大量任务。

这里主要介绍一下 ForkJoinTask 的主要特征，具体的综合使用情况，请看 ForkJoinTaskTest.md 章节；

1. ForkJoinTask的主要特征是它通常会产生新的子任务作为完成其主要任务所需工作的一部分。它通过调用fork()生成新任务，并使用join()收集所有结果，
因此是类的名称。
2. 有两个实现ForkJoinTask 的抽象类：`RecursiveTask`，它在完成时返回一个值，以及`RecursiveAction`，它不返回任何东西。顾名思义，这些类
将用于递归任务，例如文件系统导航或复杂的数学计算。

### 案例使用`RecursiveTask`完成阶乘递归
让我们扩展之前的示例以创建一个类，给定一个Integer，该类将计算其所有阶乘元素的平方和。因此，例如，如果我们将数字 4 传递给计算器，我们应该从
4² + 3² + 2² + 1² 的总和中得到结果，即 30。

首先，我们需要创建RecursiveTask的具体实现并实现其compute()方法。这是我们编写业务逻辑的地方：
```java
public class FactorialSquareCalculator extends RecursiveTask<Integer> {
 
    private Integer n;

    public FactorialSquareCalculator(Integer n) {
        this.n = n;
    }

    @Override
    protected Integer compute() {
        // 递归出口
        if (n <= 1) {
            return n;
        }

        FactorialSquareCalculator calculator = new FactorialSquareCalculator(n - 1);
        // fork 一个子线程
        calculator.fork();
        // 当前计算聚合子线程的计算
        return n * n + calculator.join();
    }
}
```

```java
ForkJoinPool forkJoinPool = new ForkJoinPool();

FactorialSquareCalculator calculator = new FactorialSquareCalculator(10);

forkJoinPool.execute(calculator);
```


