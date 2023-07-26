package lock;

import lombok.SneakyThrows;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author kangqing
 * @since 2023/7/24 19:36
 */
public class ReentrantReadWriteLockTest {

    static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    @SneakyThrows
    public static void main(String[] args) {
        final ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
        final ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();

        new Thread(() -> {
            readLock.lock();
            System.out.println("子线程！读锁");
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                readLock.unlock();
                System.out.println("读锁已解锁");
            }
        }).start();

        // 延迟 1s 防止先加写锁成功
        TimeUnit.SECONDS.sleep(1);
        // 等待读锁解锁，才能加写锁
        writeLock.lock();
        try {
            System.out.println("主线程！写锁");

        } finally {
            writeLock.unlock();
        }
    }
}
