package creative.factory.abstractfactory01.adapter;

/**
 * 目的是适配redis多个实现不同名称的情况
 * @author kangqing
 * @since 2024/3/7 22:32
 */
public interface ICacheAdapter {

    String get(String key);
    void set(String key, String val);
    void del(String key);

}
