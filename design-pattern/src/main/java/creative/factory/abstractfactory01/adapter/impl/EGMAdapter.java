package creative.factory.abstractfactory01.adapter.impl;

import creative.factory.abstractfactory01.adapter.ICacheAdapter;
import creative.factory.abstractfactory01.util.EGMUtil;

/**
 * 适配EGM的redis集群实现
 * @author kangqing
 * @since 2024/3/7 22:35
 */
public class EGMAdapter implements ICacheAdapter {
    private final EGMUtil egmUtil = new EGMUtil();
    @Override
    public String get(String key) {
        return egmUtil.getEGM(key);
    }

    @Override
    public void set(String key, String val) {
        egmUtil.setEGM(key, val);
    }

    @Override
    public void del(String key) {
        egmUtil.delEGM(key);
    }
}
