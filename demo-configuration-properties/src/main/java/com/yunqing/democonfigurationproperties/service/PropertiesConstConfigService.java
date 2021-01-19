package com.yunqing.democonfigurationproperties.service;

import java.util.Map;

/**
 * 获取配置项接口
 * @author kangqing
 * @date 2020/4/24 14:32
 */
public interface PropertiesConstConfigService {

    Map<String, String> getConfigMap();
}
