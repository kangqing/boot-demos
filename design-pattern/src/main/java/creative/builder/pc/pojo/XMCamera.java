package creative.builder.pc.pojo;

import creative.builder.pc.IPCSelector;

import java.math.BigDecimal;

/**
 * @author kangqing
 * @since 2023/7/6 09:38
 */
public class XMCamera implements IPCSelector {
    @Override
    public String name() {
        return "摄像头";
    }

    @Override
    public String brand() {
        return "小米";
    }

    @Override
    public BigDecimal price() {
        return new BigDecimal("50");
    }

    @Override
    public String desc() {
        return "小米摄像头，高清，性价比";
    }
}
