package creative.builder.pc;

/**
 * @author kangqing
 * @since 2023/7/6 09:59
 */
public class PCClient {
    public static void main(String[] args) {
        final PCBuilder builder = new PCBuilder();
        final IMenuSelector levelOne = builder.levelOne();
        System.out.println(levelOne.getDetail());

        final IMenuSelector levelTwo = builder.levelTwo();
        System.out.println(levelTwo.getDetail());

        final IMenuSelector levelThree = builder.levelThree();
        System.out.println(levelThree.getDetail());
    }
}
