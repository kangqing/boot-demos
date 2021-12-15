package lock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author kangqing
 * @since 2021/12/14 19:40
 */
@SpringBootApplication
public class DemoDistributedLockApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoDistributedLockApplication.class, args);
    }

}
