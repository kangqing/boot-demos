package com.yunqing.democonfigurationproperties.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.yunqing.democonfigurationproperties.entity.ConstEntity;
import com.yunqing.democonfigurationproperties.repository.ConstRepository;
import com.yunqing.democonfigurationproperties.service.PropertiesConstConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yx
 * @date 2020/4/24 14:37
 */
@Service
public class PropertiesConstConfigServiceImpl implements PropertiesConstConfigService {

    @Autowired
    private ConstRepository constRepository;
    /**
     * 从数据库获取配置项
     * @return
     */
    @Override
    public Map<String, String> getConfigMap() {
        Map<String, String> map = new HashMap<>();
        List<ConstEntity> list = constRepository.findAll();
        if (CollUtil.isNotEmpty(list)) {
            for (ConstEntity entity : list) {
                map.put(entity.getConfigKey(), entity.getConfigValue() == null ? entity.getDefaultValue() : entity.getConfigValue());
            }
        }
        return map;
    }
}
