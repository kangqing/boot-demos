package com.yunqing.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

// 如果没有自定义的类时才加载此默认实现
@Slf4j
@Component
@ConditionalOnMissingBean(name = "ossBatchUploadEventListener")
public class DefaultOssBatchUploadEventListener {

    @EventListener
    public void handleOssUploadEvent(OssBatchUploadEvent event) {
        // 获取上传成功的文件名列表
        List<String> uploadedFiles = event.getUploadedFiles();

        // 默认处理逻辑
        for (String fileName : uploadedFiles) {
            log.info("upload success file: {}", fileName);
        }
    }
}
