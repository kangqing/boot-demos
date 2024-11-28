package com.yunqing.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author kangqing
 * @since 2023/8/4 08:23
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class RpcRequest<T> implements Serializable {

    private String id;

    private T entity;

    public RpcRequest(T entity) {
        this.entity = entity;
    }

}