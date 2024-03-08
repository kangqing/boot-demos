package creative.factory.abstractfactory01.adapter.impl;

import creative.factory.abstractfactory01.adapter.ICacheAdapter;
import creative.factory.abstractfactory01.util.IIRUtil;

/**
 * @author kangqing
 * @since 2024/3/7 22:36
 */
public class IIRAdapter implements ICacheAdapter {
    private final IIRUtil iirUtil = new IIRUtil();
    @Override
    public String get(String key) {
        return iirUtil.getIIR(key);
    }

    @Override
    public void set(String key, String val) {
        iirUtil.setIIR(key, val);
    }

    @Override
    public void del(String key) {
        iirUtil.delIIR(key);
    }
}
