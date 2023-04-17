package creative.builder;

/**
 * @author kangqing
 * @since 2023/4/11 07:33
 */
public class CatTest {
    public static void main(String[] args) {
        Cat cat = new Cat.Builder()
                .setName("小花")
                .setAge(1)
                .build();

        System.out.println(cat.toString());

        LombokCat cat1 = LombokCat.CustomBuilder
                .builder().age(1)
                .color("红色")
                .name("小花")
                .build();

        System.out.println(cat1.toString());
    }
}
