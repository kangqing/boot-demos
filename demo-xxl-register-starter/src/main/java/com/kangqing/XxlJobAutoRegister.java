package com.kangqing;

import com.kangqing.anno.XxlRegister;
import com.kangqing.model.XxlJobGroup;
import com.kangqing.model.XxlJobInfo;
import com.kangqing.service.JobGroupService;
import com.kangqing.service.JobInfoService;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.MethodIntrospector;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author kangqing
 * @since 2023/8/14 15:39
 */
@Slf4j
@Component
@ComponentScan(basePackages = "com.kangqing") //确保XxlAutoRegisterProperties可以被扫描到
public class XxlJobAutoRegister implements ApplicationListener<ApplicationReadyEvent>, ApplicationContextAware {

    private ApplicationContext applicationContext;
    @Resource
    private JobGroupService jobGroupService;
    @Resource
    private JobInfoService jobInfoService;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext=applicationContext;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        addJobGroup();//注册执行器
        addJobInfo();//注册任务
    }

    /**
     * 自动注册执行器的代码非常简单，根据配置文件中的 appName 和 title 精确匹配查看调度中心是否已有执行器被注册过了，
     * 如果存在则跳过，不存在则新注册一个：
     */
    private void addJobGroup() {
        if (jobGroupService.preciselyCheck())
            return;

        if(jobGroupService.autoRegisterGroup())
            log.info("auto register xxl-job group success!");
    }

    /**
     * 自动注册任务的逻辑则相对复杂一些，需要完成：
     *
     * 1.通过 applicationContext 拿到 spring 容器中的所有 bean，再拿到这些 bean 中所有添加了 @XxlJob 注解的方法
     *
     * 2.对上面获取到的方法进行检查，是否添加了我们自定义的 @XxlRegister 注解，如果没有则跳过，不进行自动注册
     *
     * 3.对同时添加了 @XxlJob 和 @XxlRegister 的方法，通过执行器 id 和 jobHandler 的值判断是否已经在调度中心注册过了，如果已存在则跳过
     *
     * 4.对于满足注解条件且没有注册过的 jobHandler，调用接口注册到调度中心
     *
     */
    private void addJobInfo() {
        List<XxlJobGroup> jobGroups = jobGroupService.getJobGroup();
        XxlJobGroup xxlJobGroup = jobGroups.get(0);

        String[] beanDefinitionNames = applicationContext.getBeanNamesForType(Object.class, false, true);
        for (String beanDefinitionName : beanDefinitionNames) {
            Object bean = applicationContext.getBean(beanDefinitionName);

            Map<Method, XxlJob> annotatedMethods  = MethodIntrospector.selectMethods(bean.getClass(),
                    new MethodIntrospector.MetadataLookup<XxlJob>() {
                        @Override
                        public XxlJob inspect(Method method) {
                            return AnnotatedElementUtils.findMergedAnnotation(method, XxlJob.class);
                        }
                    });
            for (Map.Entry<Method, XxlJob> methodXxlJobEntry : annotatedMethods.entrySet()) {
                Method executeMethod = methodXxlJobEntry.getKey();
                XxlJob xxlJob = methodXxlJobEntry.getValue();

                //自动注册
                if (executeMethod.isAnnotationPresent(XxlRegister.class)) {
                    XxlRegister xxlRegister = executeMethod.getAnnotation(XxlRegister.class);
                    List<XxlJobInfo> jobInfo = jobInfoService.getJobInfo(xxlJobGroup.getId(), xxlJob.value());
                    if (!jobInfo.isEmpty()){
                        //因为是模糊查询，需要再判断一次
                        Optional<XxlJobInfo> first = jobInfo.stream()
                                .filter(xxlJobInfo -> xxlJobInfo.getExecutorHandler().equals(xxlJob.value()))
                                .findFirst();
                        if (first.isPresent())
                            continue;
                    }

                    XxlJobInfo xxlJobInfo = createXxlJobInfo(xxlJobGroup, xxlJob, xxlRegister);
                    Integer jobInfoId = jobInfoService.addJobInfo(xxlJobInfo);
                }
            }
        }
    }

    /**
     * 创建任务参数
     * @param xxlJobGroup
     * @param xxlJob
     * @param xxlRegister
     * @return
     */
    private XxlJobInfo createXxlJobInfo(XxlJobGroup xxlJobGroup, XxlJob xxlJob, XxlRegister xxlRegister){
        XxlJobInfo xxlJobInfo=new XxlJobInfo();
        xxlJobInfo.setJobGroup(xxlJobGroup.getId());
        xxlJobInfo.setJobDesc(xxlRegister.jobDesc());
        xxlJobInfo.setAuthor(xxlRegister.author());
        xxlJobInfo.setScheduleType("CRON");
        xxlJobInfo.setScheduleConf(xxlRegister.cron());
        xxlJobInfo.setGlueType("BEAN");
        xxlJobInfo.setExecutorHandler(xxlJob.value());
        xxlJobInfo.setExecutorRouteStrategy(xxlRegister.executorRouteStrategy());
        xxlJobInfo.setMisfireStrategy("DO_NOTHING");
        xxlJobInfo.setExecutorBlockStrategy("SERIAL_EXECUTION");
        xxlJobInfo.setExecutorTimeout(0);
        xxlJobInfo.setExecutorFailRetryCount(0);
        xxlJobInfo.setGlueRemark("GLUE代码初始化");
        xxlJobInfo.setTriggerStatus(xxlRegister.triggerStatus());

        return xxlJobInfo;
    }



}
