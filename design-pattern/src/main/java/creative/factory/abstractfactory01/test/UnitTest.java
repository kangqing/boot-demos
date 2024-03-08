package creative.factory.abstractfactory01.test;

import cn.hutool.cache.Cache;
import creative.factory.abstractfactory01.JDKProxy;
import creative.factory.abstractfactory01.adapter.ICacheAdapter;
import creative.factory.abstractfactory01.adapter.impl.EGMAdapter;
import creative.factory.abstractfactory01.adapter.impl.IIRAdapter;
import creative.factory.abstractfactory01.cache.CacheServiceImpl;
import lombok.extern.slf4j.Slf4j;

/**
 * @author kangqing
 * @since 2024/3/8 09:20
 */
@Slf4j
public class UnitTest {
    public static void main(String[] args) throws Exception {
        ICacheAdapter EGMProxy = JDKProxy.getProxy(CacheServiceImpl.class, new EGMAdapter());
        EGMProxy.set("kq", "123");
        log.info("输出EGM, kq ==> {}", EGMProxy.get("kq"));

        ICacheAdapter IIRProxy = JDKProxy.getProxy(CacheServiceImpl.class, new IIRAdapter());
        IIRProxy.set("kq", "456");
        log.info("输出IIR, kq ==> {}", IIRProxy.get("kq"));

        ICacheAdapter RedisProxy = JDKProxy.getProxy(CacheServiceImpl.class, new CacheServiceImpl());
        RedisProxy.set("kq", "789");
        log.info("输出单机，kq ==> {}", RedisProxy.get("kq"));
    }
}
