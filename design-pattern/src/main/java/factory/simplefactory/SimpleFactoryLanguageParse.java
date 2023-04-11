package factory.simplefactory;

import factory.IParse;

import java.util.HashMap;
import java.util.Map;

/**
 * 简单工厂语言解析
 * @author kangqing
 * @since 2023/4/10 21:18
 */
public class SimpleFactoryLanguageParse {
    public static void main(String[] args) {
        test("一");
    }

    private static void test(String param) {
        //IParse parse = LanguageFactory.createParser(param);
        IParse parse = LanguageFactoryCache.createParser(param);
        parse.handle();
    }
}

/**
 * 简单工厂方法一
 */
class LanguageFactory {

    public static IParse createParser(String param) {
        IParse parse = null;
        if ("1".equals(param)) {
            parse = new NumberHandler();
        } else if ("one".equals(param)) {
            parse = new EnglishHandler();
        } else if ("一".equals(param)) {
            parse = new ChineseHandler();
        }
        return parse;
    }
}

/**
 * 第二种
 * 带缓存的简单工程
 */
class LanguageFactoryCache {
    private static final Map<String, IParse> map = new HashMap<>();

    static {
        map.put("1", new NumberHandler());
        map.put("one", new EnglishHandler());
        map.put("一", new ChineseHandler());
    }

    public static IParse createParser(String param) {
        if (param == null || param.isEmpty()) {
            return null;
        }
        return map.get(param);
    }
}
