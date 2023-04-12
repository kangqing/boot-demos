package proxy.noProxy;

import proxy.MetricsService;
import proxy.RequestInfo;

import java.util.concurrent.TimeUnit;

/**
 * 没有使用代理时候，此类把监控耗时逻辑耦合在业务逻辑中了
 * @author kangqing
 * @since 2023/4/13 06:32
 */
public class NoProxyUserController {

    // 依赖注入
    private MetricsService service;
    // 这里使用 setter 注入
    public void setService(MetricsService service) {
        this.service = service;
    }

    public void login() throws InterruptedException {
        long start = System.currentTimeMillis();
        // 执行登录的业务逻辑
        TimeUnit.MILLISECONDS.sleep(100);
        long end = System.currentTimeMillis();
        long response = end - start;
        RequestInfo info = new RequestInfo("login", response, start);
        service.recordRequest(info);
    }

    public void register() throws InterruptedException {
        long start = System.currentTimeMillis();
        // 执行注册的业务逻辑
        TimeUnit.MILLISECONDS.sleep(100);
        long end = System.currentTimeMillis();
        long response = end - start;
        RequestInfo info = new RequestInfo("register", response, start);
        service.recordRequest(info);
    }



}
