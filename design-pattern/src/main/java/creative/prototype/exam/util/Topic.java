package creative.prototype.exam.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

/**
 * 题目类
 * @author kangqing
 * @since 2024/3/8 15:55
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Topic {
    private Map<String, String> option; // 选项：A B C D
    private String key; // 答案：B

}
