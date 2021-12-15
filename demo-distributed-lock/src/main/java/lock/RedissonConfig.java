package lock;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Redisson 配置类
 * @author kangqing
 * @since 2021/12/14 20:39
 */
@Configuration
public class RedissonConfig {

    /**
     * 单机模式
     *
     */
    @Bean
    public Redisson redissonClient() {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379").setDatabase(0);
        return (Redisson) Redisson.create(config);
    }

    /* 集群模式
    @Bean
     public RedissonClient redissonClient() {
                 Config config = new Config();
                 config.useClusterServers()
                         .setScanInterval(2000)
                         .addNodeAddress("redis://10.0.29.30:6379", "redis://10.0.29.95:6379")
                         .addNodeAddress("redis://10.0.29.205:6379");

                return Redisson.create(config);
             }

     */
}
