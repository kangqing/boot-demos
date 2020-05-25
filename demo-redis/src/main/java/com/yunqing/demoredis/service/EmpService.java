package com.yunqing.demoredis.service;

import com.yunqing.demoredis.entity.Emp;

import java.util.List;

public interface EmpService {

    List<Emp> selectAll();

    Emp getEmpById(String id);

    void add(Emp emp);

    void delete(String id);

    void testRedisRollback() throws Exception;
}
