package com.yunqing.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.util.List;

@Getter
public class OssBatchUploadEvent extends ApplicationEvent {

    private final List<String> uploadedFiles;

    public OssBatchUploadEvent(Object source, List<String> uploadedFiles) {
        super(source);
        this.uploadedFiles = uploadedFiles;
    }

}
