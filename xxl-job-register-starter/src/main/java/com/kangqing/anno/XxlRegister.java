package com.kangqing.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 在创建任务时，必填字段除了执行器和 jobHandler 之外，还有任务描述、负责人、Cron 表达式、调度类型、运行模式。
 * 在这里，我们默认调度类型为 CRON、运行模式为 BEAN，另外的 3 个字段的信息需要用户指定。
 *
 * 因此我们需要创建一个新注解 @XxlRegister，来配合原生的 @XxlJob 注解进行使用，填写这几个字段的信息：
 * @author kangqing
 * @since 2023/8/14 15:37
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface XxlRegister {
    String cron();
    String jobDesc() default "default jobDesc";
    String author() default "default Author";
    /*
     * 默认为 ROUND 轮询方式
     * 可选： FIRST 第一个
     * */
    String executorRouteStrategy() default "ROUND";
    // 表示任务的默认调度状态，0 为停止状态，1 为运行状态。
    int triggerStatus() default 0;
}
