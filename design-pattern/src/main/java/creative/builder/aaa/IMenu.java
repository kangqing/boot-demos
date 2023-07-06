package creative.builder.aaa;

/**
 * 装修内容包接口
 * @author kangqing
 * @since 2023/7/5 21:06
 */
public interface IMenu {

    IMenu appendCeiling(Matter matter); // 吊顶

    IMenu appendCoat(Matter matter); //涂料

    IMenu appendFloor(Matter matter); // 地板

    IMenu appendTile(Matter matter); // 地砖

    String getDetail(); // 明细
}
