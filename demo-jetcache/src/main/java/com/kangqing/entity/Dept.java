package com.kangqing.entity;

/**
 * @author kangqing
 * @since 2023/8/21 09:47
 */

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import com.mybatisflex.core.query.QueryColumn;
import lombok.Data;

import java.io.Serializable;

@Table("t_dept")
public class Dept implements Serializable {

    private static final long serialVersionUID = -557517290757724418L;

    @Id(keyType = KeyType.Auto)
    private Long id;

    private String deptName;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }
}
