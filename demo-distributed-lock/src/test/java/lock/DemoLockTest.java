package lock;

import lock.service.LocalLockService;
import lock.service.RedissonLockService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author kangqing
 * @since 2023/7/26 15:48
 */
@SpringBootTest
public class DemoLockTest {

    @Resource
    private LocalLockService lockService;

    @Resource
    private RedissonLockService redissonLockService;

    @Test
    void testRedissonLock() {
        for (int i = 0; i < 100; i++) {
            redissonLockService.redissonLock();
        }
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                redissonLockService.redissonLock();
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                redissonLockService.redissonLock();
            }
        }).start();

    }

    @Test
    void testLocalLock() {

        new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                System.out.println("---------------");
                lockService.localLock();
                System.out.println("***************");
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                lockService.localLock();
            }
        }).start();
    }


}
