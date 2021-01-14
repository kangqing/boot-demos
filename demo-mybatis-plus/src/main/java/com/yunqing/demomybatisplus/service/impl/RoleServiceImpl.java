package com.yunqing.demomybatisplus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yunqing.demomybatisplus.pojo.Role;
import com.yunqing.demomybatisplus.mapper.RoleMapper;
import com.yunqing.demomybatisplus.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author yunqing
 * @since 2020-01-09
 */
@Service
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    private final RoleMapper roleMapper;

    @Override
    public List<Role> getAll() {
        return roleMapper.selectList(new QueryWrapper<>());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateTest() throws IOException {
        test();
    }

    //@Transactional(rollbackFor = Exception.class)
    private void test() {
        Role role = Role.builder()
                .id(2L)
                .roleName("test1")
                .build();
        roleMapper.updateById(role);
        try {
            throw new IOException();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
