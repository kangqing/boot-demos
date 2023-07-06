package creative.builder.pc;

import creative.builder.pc.pojo.*;

/**
 * @author kangqing
 * @since 2023/7/6 09:49
 */
public class PCBuilder {

    public IMenuSelector levelOne() {
        return new PCImpl("套餐一")
                .appendMouse(new LJMouse())
                .appendKeyboard(new JiXieKeyboard())
                .appendSound(new SNSound())
                .appendCamera(new HWCamera());
    }

    public IMenuSelector levelTwo() {
        return new PCImpl("套餐二")
                .appendMouse(new LXMouse())
                .appendKeyboard(new FLPKeyboard())
                .appendSound(new LXSound())
                .appendCamera(new HWCamera());
    }

    public IMenuSelector levelThree() {
        return new PCImpl("套餐三")
                .appendMouse(new LXMouse())
                .appendKeyboard(new FLPKeyboard())
                .appendSound(new LXSound())
                .appendCamera(new XMCamera());
    }
}
