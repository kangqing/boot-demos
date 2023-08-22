package com.kangqing.service;

import com.alicp.jetcache.anno.*;
import com.kangqing.entity.Dept;
import com.kangqing.mapper.DeptMapper;
import com.mybatisflex.core.query.QueryWrapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.kangqing.entity.table.DeptTableDef.DEPT;

/**
 * @author kangqing
 * @since 2023/8/21 09:53
 */

@Service
public class DeptService {

    @Resource
    private DeptMapper deptMapper;

    @Cached(name = "test:cache", cacheType = CacheType.BOTH, localLimit = 1, key = "#id", localExpire = 30, expire = 600)
    //缓存30秒钟自动刷新，从getUserById方法取一次，如果key在600秒内没有访问则不再自动刷新
    @CacheRefresh(refresh = 30, stopRefreshAfterLastAccess = 600, timeUnit = TimeUnit.SECONDS)
    //当缓存访问未命中的情况下，对并发进行的加载行为进行保护，同一个JVM中同一个key只有一个线程去加载，其它线程等待结果
    @CachePenetrationProtect
    public Dept findById(Long id) {
        return deptMapper.selectOneById(id);
    }

    /**
     * import static com.kangqing.entity.table.DeptTableDef.DEPT;
     * 注意这块是静态导入的 DEPT.ID.eq(1L)
     * @return 部门列表
     */
    @CacheUpdate(name = "test:cache", key = "#dept.id", value = "#dept")
    public Dept update(Dept dept) {
        QueryWrapper qw = QueryWrapper.create()
                .select()
                .where(DEPT.ID.eq(dept.getId()));
        deptMapper.updateByQuery(dept, qw);
        return dept;
    }

    @CacheInvalidate(name = "test:cache", key = "#id")
    public boolean del(Long id) {
        deptMapper.deleteById(id);
        return true;
    }

    public void add(Dept dept) {
        deptMapper.insert(dept);
    }
}
