package structural.proxy.basicExtends;

import structural.proxy.MetricsService;

/**
 * @author kangqing
 * @since 2023/4/13 07:05
 */
public class BasicExtendsClient {
    public static void main(String[] args) {
        final MetricsService metricsService = new MetricsService();
        final UserControllerProxy proxy = new UserControllerProxy();
        proxy.login();
        proxy.register();
    }
}
