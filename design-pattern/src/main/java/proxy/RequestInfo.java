package proxy;

import lombok.AllArgsConstructor;
import lombok.ToString;

/**
 * @author kangqing
 * @since 2023/4/13 06:44
 */
@AllArgsConstructor
@ToString
public class RequestInfo {

    private String name;
    private long responseTime;
    private long startTime;
}
