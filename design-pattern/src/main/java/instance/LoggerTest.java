package instance;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author kangqing
 * @since 2023/4/7 21:42
 */
public class LoggerTest {

    static ExecutorService exec = Executors.newFixedThreadPool(5);

    public static void main(String[] args) {
        exec.execute(() -> {
            Logger logger = new Logger();
            logger.log("1 \n 2 \n 3 \n 4 \n");
        });
        exec.execute(() -> {
            Logger logger = new Logger();
            logger.log("5 \n 6 \n 7 \n 8 \n");
        });
        exec.execute(() -> {
            Logger logger = new Logger();
            logger.log("0 \n 0 \n 0 \n 0 \n");
        });
        exec.execute(() -> {
            Logger logger = new Logger();
            logger.log("1 \n 2 \n 3 \n 4 \n");
        });
        exec.execute(() -> {
            Logger logger = new Logger();
            logger.log("5 \n 6 \n 7 \n 8 \n");
        });
        exec.execute(() -> {
            Logger logger = new Logger();
            logger.log("0 \n 0 \n 0 \n 0 \n");
        });
        exec.shutdown();
    }
}
