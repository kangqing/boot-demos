package com.yunqing.demo.elasticsearch;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author yx
 * @since 2020/10/22 16:19
 */
public interface PersonService {

    List<Person> findAll();

    Person add(Person person);

    void delete(String id);

    Page<Person> conditionPageSearch(String field, String keyword, Pageable pageable);
}
