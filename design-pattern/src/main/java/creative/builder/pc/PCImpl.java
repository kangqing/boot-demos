package creative.builder.pc;

import creative.builder.aaa.Matter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 * @author kangqing
 * @since 2023/7/6 09:43
 */
public class PCImpl implements IMenuSelector{

    private List<IPCSelector> list = new ArrayList<>();
    private BigDecimal price = BigDecimal.ZERO;
    private String type; //套餐类型 1, 2, 3

    private PCImpl() {}

    /**
     * 初始化套餐
     * @param type
     */
    public PCImpl(String type) {
        this.type = type;
    }

    @Override
    public IMenuSelector appendKeyboard(IPCSelector ipcSelector) {
        list.add(ipcSelector);
        price = price.add(ipcSelector.price());
        return this;
    }

    @Override
    public IMenuSelector appendMouse(IPCSelector ipcSelector) {
        list.add(ipcSelector);
        price = price.add(ipcSelector.price());
        return this;
    }

    @Override
    public IMenuSelector appendSound(IPCSelector ipcSelector) {
        list.add(ipcSelector);
        price = price.add(ipcSelector.price());
        return this;
    }

    @Override
    public IMenuSelector appendCamera(IPCSelector ipcSelector) {
        list.add(ipcSelector);
        price = price.add(ipcSelector.price());
        return this;
    }

    @Override
    public String getDetail() {
        StringBuilder detail = new StringBuilder("\r\n--------------------" +
                "-----------------------------------\r\n" +
                "外设清单" + "\r\n" +
                "套餐选择：" + type + "\r\n" +
                "套餐价格：" + price.setScale(2, RoundingMode.HALF_UP) + " 元\r\n" +
                "外设清单：\r\n");
        for (IPCSelector pc: list) {
            detail.append(pc.name()).append("：").append(pc.brand()).append(
                    "、").append(pc.price()).append(" 元、介绍：").append(pc.desc())
                    .append("\r\n");
        }
        return detail.toString();
    }
}
