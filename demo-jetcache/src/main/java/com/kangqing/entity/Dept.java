package com.kangqing.entity;

/**
 * @author kangqing
 * @since 2023/8/21 09:47
 */

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import com.mybatisflex.core.query.QueryColumn;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Table("t_dept")
@Schema(description = "部门信息")
public class Dept implements Serializable {

    private static final long serialVersionUID = -557517290757724418L;

    @Id(keyType = KeyType.Auto)
    @Schema(description = "部门id")
    private Long id;

    @Schema(description = "部门名")
    private String deptName;
}
