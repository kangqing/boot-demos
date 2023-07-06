package creative.builder.aaa;

import creative.builder.aaa.matter.*;

/**
 * @author kangqing
 * @since 2023/7/5 21:29
 */
public class Builder {

    public IMenu levelOne(Double area) {
        return new DecorationPackageMenu(area, "豪华欧式")
                .appendCeiling(new LevelTwoCeiling()) // 吊顶，二级顶
                .appendCoat(new DuluxCoat()) // 涂料，多乐士
                .appendFloor(new ShengXiangFloor()) // 地板圣象
                .appendTile(new MarcoPoloTile()); // 地砖，马可波罗
    }

    public IMenu levelTwo(Double area){
        return new DecorationPackageMenu(area, "轻奢⽥园")
                .appendCeiling(new LevelTwoCeiling()) // 吊顶，⼆级顶
                .appendCoat(new LiBangCoat()) // 涂料，⽴邦
                .appendTile(new MarcoPoloTile()); // 地砖，⻢可波罗
    }
    public IMenu levelThree(Double area){
        return new DecorationPackageMenu(area, "现代简约")
                .appendCeiling(new LevelOneCeiling()) // 吊顶，⼆级顶
                .appendCoat(new LiBangCoat()) // 涂料，⽴邦
                .appendTile(new DongPengTile()); // 地砖，东鹏
    }
}
