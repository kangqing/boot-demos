package creative.builder.pc.pojo;

import creative.builder.pc.IPCSelector;

import java.math.BigDecimal;

/**
 * @author kangqing
 * @since 2023/7/6 09:34
 */
public class LXMouse implements IPCSelector {
    @Override
    public String name() {
        return "鼠标";
    }

    @Override
    public String brand() {
        return "联想";
    }

    @Override
    public BigDecimal price() {
        return new BigDecimal("29");
    }

    @Override
    public String desc() {
        return "联想鼠标，高端商务定制，办公鼠标";
    }
}
