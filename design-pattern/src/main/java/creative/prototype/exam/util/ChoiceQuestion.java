package creative.prototype.exam.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * @author kangqing
 * @since 2024/3/8 17:00
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChoiceQuestion {

    private String name; // 题目
    private Map<String, String> option; // 选项
    private String key; // 答案
}
