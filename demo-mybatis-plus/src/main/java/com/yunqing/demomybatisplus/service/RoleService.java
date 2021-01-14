package com.yunqing.demomybatisplus.service;

import com.yunqing.demomybatisplus.pojo.Role;
import com.baomidou.mybatisplus.extension.service.IService;

import java.io.IOException;
import java.util.List;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author yunqing
 * @since 2020-01-09
 */
public interface RoleService extends IService<Role> {

    List<Role> getAll();

    void updateTest() throws IOException;

}
