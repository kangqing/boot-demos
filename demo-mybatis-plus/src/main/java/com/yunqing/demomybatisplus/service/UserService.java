package com.yunqing.demomybatisplus.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yunqing.demomybatisplus.dto.PageDTO;
import com.yunqing.demomybatisplus.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author yunqing
 * @since 2020-01-09
 */
public interface UserService extends IService<User> {

    IPage<User> getAll(PageDTO pageDTO);

    IPage<User> getListByEmail(PageDTO pageDTO, String email);

}
