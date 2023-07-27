package lock.conf4j;

import com.baomidou.lock.DefaultLockKeyBuilder;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.stereotype.Component;

/**
 * 自定义分布式锁的 key 生产规则
 * @author kangqing
 * @since 2023/7/27 16:14
 */
@Component
public class CustomKeyBuilder extends DefaultLockKeyBuilder {

    public CustomKeyBuilder(BeanFactory beanFactory) {
        super(beanFactory);
    }
}