package com.yunqing.democonfigurationproperties.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author kangqing
 * @date 2020/4/24 14:51
 */
@Data
@Entity
@Table(name = "t_const_config")
public class ConstEntity implements Serializable {

    /**
     * 主键id
     */
    @Id
    private Integer id;

    /**
     * 字典Key，组内不能相同
     */
    private String configKey;

    /**
     * 字典值
     */
    private String configValue;

    /**
     * 默认字典值
     */
    private String defaultValue;

    /**
     * 描述
     */
    private String description;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}
