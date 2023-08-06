package com.yunqing.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author kangqing
 * @since 2023/8/4 08:23
 */
@NoArgsConstructor
@Accessors(chain = true)
public class RpcRequest<T> implements Serializable {

    private String id;

    private T entity;

    public RpcRequest(T entity) {
        this.entity = entity;
    }

    public RpcRequest(String id, T entity) {
        this.id = id;
        this.entity = entity;
    }

    public String getId() {
        return id;
    }

    public T getEntity() {
        return entity;
    }
}