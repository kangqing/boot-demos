package com.yunqing.demoatest.utils;

import java.math.BigDecimal;

/**
 * @author yx
 * @description 函数式接口，用于Stream()流处理BigDecimal
 * @date 2020/7/6 12:13
 */
@FunctionalInterface
public interface ToBigDecimalFunction<T> {
    BigDecimal applyAsBigDecimal(T value);
}
