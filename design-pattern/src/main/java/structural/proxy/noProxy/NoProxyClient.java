package structural.proxy.noProxy;

import structural.proxy.MetricsService;

/**
 * @author kangqing
 * @since 2023/4/13 06:47
 */
public class NoProxyClient {

    public static void main(String[] args) throws InterruptedException {
        final MetricsService metricsService = new MetricsService();
        NoProxyUserController userController = new NoProxyUserController();
        userController.setService(metricsService);

        userController.login();
        userController.register();
    }
}
