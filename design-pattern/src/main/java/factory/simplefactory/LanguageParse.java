package factory.simplefactory;

import factory.IParse;

/**
 * 不使用工厂方法时候
 * @author kangqing
 * @since 2023/4/10 21:07
 */
public class LanguageParse {
    public static void main(String[] args) {
        test("one");
    }

    public static void test(String param) {
        IParse parse = null;
        if ("1".equals(param)) {
            parse = new NumberHandler();
        } else if ("one".equals(param)) {
            parse = new EnglishHandler();
        } else if ("一".equals(param)) {
            parse = new ChineseHandler();
        }
        assert parse != null;
        parse.handle();
    }
}

class NumberHandler implements IParse{

    @Override
    public void handle() {
        System.out.println("阿拉伯解析");
    }
}
class EnglishHandler implements IParse{

    @Override
    public void handle() {
        System.out.println("英文解析");
    }
}
class ChineseHandler implements IParse{

    @Override
    public void handle() {
        System.out.println("中文解析");
    }
}