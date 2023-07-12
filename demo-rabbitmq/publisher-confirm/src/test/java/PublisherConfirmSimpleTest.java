import com.kangqing.PublisherConfirmSimpleApplication;
import com.kangqing.correlated.CorrelatedProducer;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.kangqing.simple.PublisherConfirmProducer;

import javax.annotation.Resource;
import java.util.concurrent.CountDownLatch;

/**
 * @author kangqing
 * @since 2023/7/11 16:11
 */
@Slf4j
@SpringBootTest(classes = PublisherConfirmSimpleApplication.class)
public class PublisherConfirmSimpleTest {

    @Resource
    private PublisherConfirmProducer producer;

    @Resource
    private CorrelatedProducer asyncProducer;

    /**
     * 同步确认发送
     * @throws InterruptedException
     */
    @Test
    public void testSyncSend() throws InterruptedException {
        int id = (int) (System.currentTimeMillis() / 1000);
        producer.syncSend(id);
        log.info("[testSyncSend][发送编号：[{}] 发送成功]", id);
    }

    /**
     * 异步确认发送
     * @throws InterruptedException
     */
    @Test
    public void testAsyncSend() throws InterruptedException {
        int id = (int) (System.currentTimeMillis() / 1000);
        asyncProducer.syncSend(id);
        log.info("[testAsyncSend][发送编号：[{}] 发送成功]", id);

    }
}
