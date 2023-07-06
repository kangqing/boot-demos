package creative.builder.pc.pojo;

import creative.builder.pc.IPCSelector;

import java.math.BigDecimal;

/**
 * @author kangqing
 * @since 2023/7/6 09:39
 */
public class HWCamera implements IPCSelector {
    @Override
    public String name() {
        return "摄像头";
    }

    @Override
    public String brand() {
        return "华为";
    }

    @Override
    public BigDecimal price() {
        return new BigDecimal("79");
    }

    @Override
    public String desc() {
        return "华为摄像头，高清，高端外设";
    }
}
