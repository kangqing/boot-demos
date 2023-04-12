package proxy;

import lombok.AllArgsConstructor;
import lombok.ToString;

/**
 * @author kangqing
 * @since 2023/4/13 06:34
 */
public class MetricsService {

     public void recordRequest(RequestInfo info) {
         System.out.println(info.toString());
     }
}

