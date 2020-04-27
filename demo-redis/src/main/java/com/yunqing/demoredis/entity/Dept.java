package com.yunqing.demoredis.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

@Data
@TableName("t_dept")
public class Dept extends Model<Dept> {

    private String id;

    private String deptName;

}
