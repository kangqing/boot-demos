package adapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 适配器模式，SLF4J 适配了各种框架
 * 代理模式、装饰器模式、适配器模式，这三种设计模式很像，三种都是 Wrapper 设计模式
 * 应用场景：
 * 1. 代理模式：在不改变原始类接口的条件下，为原始类创建一个代理类，主要目的是控制访问；
 * 2. 装饰器模式：在不改变原始类接口的条件下，为原始类创建一个装饰器类，对原始类功能进行增强；
 * 3. 适配器模式：是一种补救措施，适配器提供与原始类不同的接口，补救原始类的设计缺陷，而代理类和装饰器类，需要提供与原始类相同的接口。
 *
 * Google Guava 的collection包下，提供了一组 Forwarding开头的扩展类，其作用是为了简化 Wrapper 模式的实现，基于 此组扩展类，可以方便
 * 的继承扩展类，但是只重新实现自己关心方法，其他不关心的方法使用扩展类的默认实现。
 * @author kangqing
 * @since 2023/4/17 07:10
 */
public class Client {

    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(Client.class);
        logger.error("1234");
    }
}
