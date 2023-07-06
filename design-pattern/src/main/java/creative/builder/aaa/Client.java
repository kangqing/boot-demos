package creative.builder.aaa;

/**
 * @author kangqing
 * @since 2023/7/5 21:32
 */
public class Client {
    public static void main(String[] args) {
        Builder builder = new Builder();
        // 豪华欧式
        System.out.println(builder.levelOne(132.52D).getDetail());
        // 轻奢⽥园
        System.out.println(builder.levelTwo(98.25D).getDetail());
        // 现代简约
        System.out.println(builder.levelThree(85.43D).getDetail());
    }
}
