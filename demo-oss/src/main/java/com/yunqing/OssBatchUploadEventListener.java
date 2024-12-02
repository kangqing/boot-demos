package com.yunqing;

import com.yunqing.event.OssBatchUploadEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

//@Slf4j
//@Component
//public class OssBatchUploadEventListener {
//
//    @EventListener
//    public void handleOssUploadEvent(OssBatchUploadEvent event) {
//        // 获取上传成功的文件名列表
//        List<String> uploadedFiles = event.getUploadedFiles();
//
//        // 默认处理逻辑
//        for (String fileName : uploadedFiles) {
//            log.info("111upload success file: {}", fileName);
//        }
//    }
//}
