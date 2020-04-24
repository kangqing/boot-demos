package com.yunqing.democonfigurationproperties.repository;

import com.yunqing.democonfigurationproperties.entity.ConstEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author yx
 * @date 2020/4/24 14:55
 */
public interface ConstRepository extends JpaRepository<ConstEntity, Integer> {
}
