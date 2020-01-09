package com.yunqing.demomybatisplus.service.impl;

import com.yunqing.demomybatisplus.pojo.User;
import com.yunqing.demomybatisplus.mapper.UserMapper;
import com.yunqing.demomybatisplus.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author yunqing
 * @since 2020-01-09
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
