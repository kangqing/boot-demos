package com.kangqing.demoeasyes.entity.es;

import lombok.Data;
import org.dromara.easyes.annotation.IndexName;

/**
 * *****************************************************
 * <a href="https://gitee.com/dromara/easy-es/pulls/86/files">...</a>
 * bug 此类不加 @IndexName 会导致增删改获取不到刷新策略，空指针
 * *****************************************************
 * @author kangqing
 * @since 2023/8/24 21:14
 */
@Data
@IndexName
public class Document {
    /**
     * es中的唯一id
     */
    private String id;
    /**
     * 文档标题
     */
    private String title;
    /**
     * 文档内容
     */
    private String content;
}
