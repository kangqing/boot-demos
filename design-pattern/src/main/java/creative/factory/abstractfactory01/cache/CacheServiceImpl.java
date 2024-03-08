package creative.factory.abstractfactory01.cache;

import creative.factory.abstractfactory01.adapter.ICacheAdapter;
import creative.factory.abstractfactory01.util.RedisUtil;

/**
 * @author kangqing
 * @since 2024/3/8 09:18
 */
public class CacheServiceImpl implements ICacheAdapter {

    private final RedisUtil redisUtil = new RedisUtil();

    @Override
    public String get(String key) {
        return redisUtil.get(key);
    }

    @Override
    public void set(String key, String value) {
        redisUtil.set(key, value);
    }

    @Override
    public void del(String key) {
        redisUtil.del(key);
    }
}
