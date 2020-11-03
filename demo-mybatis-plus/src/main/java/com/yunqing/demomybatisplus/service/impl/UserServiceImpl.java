package com.yunqing.demomybatisplus.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yunqing.demomybatisplus.dto.PageDTO;
import com.yunqing.demomybatisplus.pojo.User;
import com.yunqing.demomybatisplus.mapper.UserMapper;
import com.yunqing.demomybatisplus.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    private final UserMapper userMapper;

    @Override
    public IPage<User> getAll(PageDTO pageDTO) {
        IPage<?> page = PageDTO.buildPageCondition(pageDTO);
        return userMapper.getAll(page);
    }
}
