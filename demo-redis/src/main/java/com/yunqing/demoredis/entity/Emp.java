package com.yunqing.demoredis.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("t_emp")
public class Emp extends Model<Emp> {

    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    private String name;

    private Integer age;

    private String mobile;

    private String gender;

    private String deptId;
}
