import java.time.LocalDateTime;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * 函数式接口
 * @author kangqing
 * @since 2023/10/18 19:22
 */
public class FunctionInterfaceTest {
    public static void main(String[] args) {
        final String supplier = supplier(() -> {
            final LocalDateTime now = LocalDateTime.now();
            return now.toString();
        });
        System.out.println("生产型接口返回的是时间 ==>" + supplier);

        int[] arr = {1,2,3,6,54,4};
        final String supplier1 = supplier(() -> {
            int max = arr[0];
            for (int i = 1; i < arr.length; i++) {
                if (arr[i] > max) {
                    max = arr[i];
                }
            }
            return String.valueOf(max);
        });
        System.out.println("生产型接口返回的是最大值 ==>" + supplier1);

        //------------------------------

        consumer("yunqing", System.out::println);
        consumer2("kangqing", System.out::println,
                name -> System.out.println(new StringBuilder(name).reverse()));

        //------------------------------
        final boolean boo = predicate("yunqing", name -> name.length() > 8);
        System.out.println(boo);

        final boolean b = predicateAnd("yunqing", name -> name.length() > 3,
                name -> name.length() < 8);
        System.out.println(b);

        //------------------------------
        final String func = func(10, String::valueOf);
        System.out.println("处理结果 ==>" + func);

        // 自定义函数式接口
        final String yunqing = abc("yunqing", 18, (a, m) -> a + m);
        System.out.println(yunqing);
    }

    /**
     * 生产型接口
     * @param supplier 执行生产的逻辑，不需要参数
     * @return 返回生产的结果
     */
    public static String supplier(Supplier<String> supplier) {
        return supplier.get();
    }

    /**
     * 消费型接口
     * @param name 参数
     * @param consumer 接收参数，进行消费
     */
    public static void consumer(String name, Consumer<String> consumer) {
        consumer.accept(name);
    }

    /**
     * 消费型接口，一个参数两次处理
     * @param name 参数
     * @param x 第一次处理
     * @param y 第二次处理
     */
    public static void consumer2(String name, Consumer<String> x, Consumer<String> y) {
        x.accept(name);
        y.accept(name);
        // 上面相当于
        //x.andThen(y).accept(name);
    }

    /**
     * boolean test(T t)：对给定的参数进行判断（判断逻辑由Lambda表达式实现），返回一个布尔值。
     * default Predicate<T> negate()：返回一个逻辑的否定，对应逻辑非。
     * default Predicate<T> and(Predicate other)：返回一个组合判断，对应短路与。
     * default Predicate<T> or(Predicate other)：返回一个组合判断，对应短路或。
     * Predicate接口通常用于判断参数是否满足指定的条件
     */
    public static boolean predicate(String name, Predicate<String> predicate) {
        return predicate.test(name);
    }

    /**
     * 对一个参数经过两次判断，取and
     * @param name 参数
     * @param x 判断一逻辑
     * @param y 判断二逻辑
     * @return x && y
     */
    public static boolean predicateAnd(String name, Predicate<String> x, Predicate<String> y) {
        return x.and(y).test(name);
    }

    /**
     * 接收一个参数，返回一个值
     * @param num 接收的参数
     * @param function 处理接收的参数
     * @return 返回处理结果
     */
    public static String func(Integer num, Function<Integer, String> function) {
        return function.apply(num);
    }

    public static String abc(String name, Integer age, Abc<String, Integer, String> abc) {
        return abc.apply(name, age);
    }
}

/**
 * 自定义多参数，返回一个值的函数式接口
 * @param <A> 参数1
 * @param <B> 参数2
 * @param <R> 返回值
 */
@FunctionalInterface
interface Abc<A, B, R> {
    R apply(A a, B b);
}



