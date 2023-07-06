package creative.builder.pc;

import java.math.BigDecimal;

/**
 * @author kangqing
 * @since 2023/7/6 09:21
 */
public interface IPCSelector {

    String name(); // 外设 键盘、鼠标、音响、摄像头
    String brand(); // 品牌
    BigDecimal price(); // 价格
    String desc(); // 描述
}
