package com.yunqing.demoredis.service.impl;

import com.yunqing.demoredis.config.RedisCacheTemplate;
import com.yunqing.demoredis.entity.Emp;
import com.yunqing.demoredis.mapper.EmpMapper;
import com.yunqing.demoredis.service.EmpService;
import com.yunqing.demoredis.utils.SequenceUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
@Transactional
public class EmpServiceImpl implements EmpService {

    private static final String  SERIAL_NUM= "redis:serialNumber:";

    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private RedisCacheTemplate redisCacheTemplate;

    @Override
    public List<Emp> selectAll() {
        return empMapper.selectList(null);
    }

    @Override
    @Cacheable(value = "empCache", key = "#id", sync = true)
    public Emp getEmpById(String id) {
        return empMapper.selectById(id);
    }

    @Override
    @CachePut(value = "empCache", key = "#emp.id")
    public void add(Emp emp) {
        empMapper.insert(emp);
    }

    @Override
    @CacheEvict(value = "empCache",key = "#id")
    public void delete(String id) {
        empMapper.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void testRedisRollback() {
        String currentDate = new SimpleDateFormat("yyyyMMdd").format(new Date());
        String key = SERIAL_NUM + currentDate;
        long incr = redisCacheTemplate.incr(key, 1, 86400);
        //移位运算符<<，左移几位相当于乘以2的几次方, 1 << 2 = 4
        String code = SequenceUtil.getSequence(incr, 1 << 2);
        log.info(currentDate + code);
        try {
            throw new Exception("自定义异常");
        } catch (Exception e) {
            e.printStackTrace();
            redisCacheTemplate.decr(key, 1);

        }
    }
}
