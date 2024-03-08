package creative.prototype.exam.util;

import java.util.*;

/**
 * 让选项乱序，答案乱序
 * @author kangqing
 * @since 2024/3/8 15:58
 */
public class TopicRandomUtil {
    /**
     * 乱序Map元素，记录对应答案key
     * @param option 题目
     * @param key    答案
     * @return Topic 乱序后 {A=c., B=d., C=a., D=b.}
     */
    static public Topic random(Map<String, String> option, String key) {
        Set<String> keySet = option.keySet();
        ArrayList<String> keyList = new ArrayList<>(keySet);
        // 随机洗牌，重排序列表的选项
        Collections.shuffle(keyList);
        HashMap<String, String> optionNew = new HashMap<>();
        int idx = 0;
        String keyNew = "";
        for (String next : keySet) {
            String randomKey = keyList.get(idx++);
            if (key.equals(next)) {
                // 新的答案位置，A
                keyNew = randomKey;
            }
            // 重新排列后，重构题目选项对应的答案
            optionNew.put(randomKey, option.get(next));
        }
        return new Topic(optionNew, keyNew);
    }
}
