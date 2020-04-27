package com.yunqing.demoredis.service.impl;

import com.yunqing.demoredis.entity.Emp;
import com.yunqing.demoredis.mapper.EmpMapper;
import com.yunqing.demoredis.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;

    @Override
    @Cacheable(value = {"selectAll"}, unless="#result == null")
    public List<Emp> selectAll() {
        return empMapper.selectList(null);
    }

    @Override
    @CachePut(value = {"selectAll"}, key = "#emp.id", unless="#result == null")
    @Transactional(rollbackFor = Exception.class)
    public void add(Emp emp) {
        empMapper.insert(emp);
    }

    @Override
    @CacheEvict(value = {"selectAll"}, key = "#id")
    @Transactional(rollbackFor = Exception.class)
    public void delete(String id) {
        empMapper.deleteById(id);
    }
}
